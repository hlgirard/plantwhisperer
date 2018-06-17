package data;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import java.util.List;

public class MoistureHistoryViewModel extends AndroidViewModel {

    private MoistureHistoryRepository mRepository;
    private LiveData<List<MoistureHistory>> mAllHistory;

    public MoistureHistoryViewModel(@NonNull Application application) {
        super(application);
        mRepository = new MoistureHistoryRepository(application);
        mAllHistory = mRepository.getAllMoistureHistory();
    }

    // Wrappers for all methods

    public LiveData<List<MoistureHistory>> getAllHistory() { return mAllHistory; }

    public List<MoistureHistory> getAllHistoryList() { return mRepository.getAllHistoryList(); }

    public List<MoistureHistory> getHistoryByPlantId(int plantId) { return mRepository.getHistoryByPlantId(plantId); }

    public List<MoistureHistory> getHistoryByIdLaterThan(int plantId, long time) { return mRepository.getHistoryByIdLaterThan(plantId, time); }

    public void deleteAllOlderThan(long time) { mRepository.deleteAllOlderThan(time);}

    public void insert(MoistureHistory data) { mRepository.insert(data); }

    public void update(MoistureHistory data) { mRepository.update(data); }

    public void delete(MoistureHistory data) { mRepository.delete(data); }
}
