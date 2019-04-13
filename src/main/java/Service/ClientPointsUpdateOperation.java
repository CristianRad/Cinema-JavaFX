package Service;

import Domain.Entity;
import Repository.IRepository;

import java.util.List;

public class ClientPointsUpdateOperation<T extends Entity> extends UndoRedoOperation {

    private List<T> currentCards;
    private List<T> updatedCards;

    public ClientPointsUpdateOperation(IRepository repository, List<T> currentCards, List<T> updatedCards) {
        super(repository);
        this.currentCards = currentCards;
        this.updatedCards = updatedCards;
    }

    @Override
    public void doUndo() {
        for (T entity: currentCards)
            repository.update(entity);
    }

    @Override
    public void doRedo() {
        for (T entity: updatedCards)
            repository.update(entity);
    }

}
