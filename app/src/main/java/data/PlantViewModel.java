package data;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;

import java.util.List;

public class PlantViewModel extends AndroidViewModel {

    private PlantRepository mRepository;
    private LiveData<List<Plant>> mAllPlants;

    public PlantViewModel(@NonNull Application application) {
        super(application);
        mRepository = new PlantRepository(application);
        mAllPlants = mRepository.getAllPlants();
    }

    // Wrapper for the get methods
    public LiveData<List<Plant>> getAllPlants() { return mAllPlants; }

    public List<Plant> getPlantList() { return mRepository.getPlantList(); }

    public Plant getPlantById(int id) { return mRepository.getPlantById(id); }

    public Plant getPlantByTopic(String topic) { return mRepository.getPlantByTopic(topic); }

    // Wrapper for the repository methods method
    public void insert(Plant plant) { mRepository.insert(plant); }

    public void update(Plant plant) { mRepository.update(plant); }

    public void delete(Plant plant) { mRepository.delete(plant); }
}
