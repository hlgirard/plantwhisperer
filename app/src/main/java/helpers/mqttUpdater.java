package helpers;

import android.content.Context;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.View;

import com.hlgirard.android.plantwhisperer.R;

import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallbackExtended;
import org.eclipse.paho.client.mqttv3.MqttMessage;

import java.util.Calendar;
import java.util.List;

import data.Plant;
import data.PlantViewModel;
import helpers.MqttHelper;

public class mqttUpdater {

    PlantViewModel mPlantViewModel;

    Context context;

    public mqttUpdater(Context context) {
        this.context = context.getApplicationContext();
    }

    private void updateAllPlants() {
        List<Plant> plantList = mPlantViewModel.getPlantList();

        String mqttTopic;
        Plant currentPlant;

        // TODO: transfer this logic to the MainActivity
        /*if (plantList.size() == 0) {
            loading_spinner.setVisibility(View.GONE);
            Snackbar mySnackbar = Snackbar.make(findViewById(R.id.parent_view), "Add a plant to get started", Snackbar.LENGTH_LONG);
            mySnackbar.show();
        }*/

        for (int i = 0; i < plantList.size(); i++) {
            currentPlant = plantList.get(i);
            mqttTopic = currentPlant.getTopic();
            Log.v("updateAllPlants", "Starting the MQTT activity for plant #" + currentPlant.getId() + " with topic " + mqttTopic);
            startMqtt(mqttTopic, currentPlant.getId());
        }
    }

    private void updatePlantData(String mqttMessage, int plantIndex) {

        Log.v("updatePlantData", "Got MQTT data, starting database update with plantIndex " + plantIndex);

        Plant currentPlant = mPlantViewModel.getPlantById(plantIndex);

        Log.v("updatePlantData", "Updating the database for plant #" + currentPlant.getId());

        if (mqttMessage != null && !mqttMessage.isEmpty()) {

            // MQTT message is of form "58.00", must be converted to double and then integer before storing in database
            double mqttMessage_dbl = 0;
            {
                try {
                    mqttMessage_dbl = Double.parseDouble(mqttMessage);
                } catch (Exception e) {
                    Log.e("String2Int", "Bad conversion to integer", e);
                }
            }
            int newMoistureInt = (int) Math.floor(mqttMessage_dbl);

            currentPlant.setHumidityLevel(newMoistureInt);
            currentPlant.setDateUpdated(Calendar.getInstance().getTime().getTime());

            mPlantViewModel.update(currentPlant);

            Log.v("updatePlantData", "Updated the data for plant #" + currentPlant.getId());

        }
    }

    private void startMqtt(String topic, final int plantIndex) {
        MqttHelper mqttHelper = new MqttHelper(context, topic);
        mqttHelper.setCallback(new MqttCallbackExtended() {
            @Override
            public void connectComplete(boolean b, String s) {

            }

            @Override
            public void connectionLost(Throwable throwable) {

            }

            @Override
            public void messageArrived(String topic, MqttMessage mqttMessage) throws Exception {
                Log.w("Debug", "Received MQTT message: " + mqttMessage.toString());
                updatePlantData(mqttMessage.toString(), plantIndex);
            }

            @Override
            public void deliveryComplete(IMqttDeliveryToken iMqttDeliveryToken) {

            }
        });
    }

}