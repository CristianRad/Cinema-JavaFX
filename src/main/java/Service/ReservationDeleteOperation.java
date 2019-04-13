package Service;

import Domain.Entity;
import Repository.IRepository;

import java.util.List;

public class ReservationDeleteOperation<T extends Entity> extends UndoRedoOperation {

    private List<T> removedEntities;

    public ReservationDeleteOperation(IRepository repository, List<T> removedEntities) {
        super(repository);
        this.removedEntities = removedEntities;
    }

    @Override
    public void doUndo() {
        for (T entity : removedEntities)
            repository.insert(entity);
    }

    @Override
    public void doRedo() {
        for (T entity : removedEntities)
            repository.remove(entity.getId());
    }

}