package Service;

import Domain.Client;
import Repository.IRepository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class ClientService {

    private IRepository<Client> clientRepository;
    private Stack<UndoRedoOperation<Client>> undoableOperations = new Stack<>();
    private Stack<UndoRedoOperation<Client>> redoableOperations = new Stack<>();

    /**
     * Instantiates a service for clients.
     * @param clientRepository is the repository used.
     */

    public ClientService(IRepository<Client> clientRepository) {
        this.clientRepository = clientRepository;
    }

    /**
     * Adds a client.
     * @param id is the ID of the client to add.
     * @param name is the name of the client to add.
     * @param surname is the surname of the client to add.
     * @param cnp is the CNP of the client to add.
     * @param birthday is the birthday of the client to add.
     * @param registrationDay is the registration day of the client to add.
     * @param points is the total of points on the card of the client to add.
     * @throws ClientServiceException if a client with the ID to add already exists.
     */

    public void addClient(String id, String name, String surname, String cnp, LocalDate birthday, LocalDate registrationDay, int points) {
        Client client = new Client(id, name, surname, cnp, birthday, registrationDay, points);
        if (clientRepository.getStorage().containsKey(id))
            throw new ClientServiceException(String.format("A client with the ID %s already exists!", id));
        clientRepository.insert(client);
        undoableOperations.add(new AddOperation<>(clientRepository, client));
        redoableOperations.clear();
    }

    /**
     * Updates a client.
     * @param id is the new ID of the client.
     * @param name is the new name of the client.
     * @param surname is the new surname of the client.
     * @param cnp is the new CNP of the client.
     * @param birthday is the new birthday of the client.
     * @param registrationDay is the new registration day of the client.
     * @param points is the new total of points on the card of the client.
     * @throws ClientServiceException if there is no client with the given ID to update.
     */

    public void updateClient(String id, String name, String surname, String cnp, LocalDate birthday, LocalDate registrationDay, int points) {
        Client client = new Client(id, name, surname, cnp, birthday, registrationDay, points);
        if (!clientRepository.getStorage().containsKey(id))
            throw new ClientServiceException(String.format("There is no client with the ID %s!", id));
        undoableOperations.add(new UpdateOperation<>(clientRepository, clientRepository.findById(id), client));
        redoableOperations.add(new UpdateOperation<>(clientRepository, client, clientRepository.findById(id)));
        clientRepository.update(client);
    }

    /**
     * Removes a client.
     * @param id is the ID of the client to remove.
     * @throws ClientServiceException if there is no client with the given ID to remove.
     */

    public void removeClient(String id) {
        if (!clientRepository.getStorage().containsKey(id))
            throw new ClientServiceException(String.format("There is no client with the ID %s!", id));
        undoableOperations.add(new RemoveOperation<>(clientRepository, clientRepository.findById(id)));
        redoableOperations.clear();
        clientRepository.remove(id);
    }

    /**
     * @return a list of all clients.
     */

    public List<Client> getAllClients() {
        return clientRepository.getAll();
    }

    /**
     * Searches clients whose fields contain a given text.
     * @param text is the text searched for.
     * @return a list of clients whose fields contain text.
     */

    public List<Client> fullTextSearch(String text) {
        List<Client> results = new ArrayList<>();
        for (Client client : getAllClients())
            if (client.getName().contains(text) || client.getSurname().contains(text) || client.getCnp().contains(text) ||
                    client.getBirthday().toString().contains(text) || client.getRegistrationDay().toString().contains(text) ||
                    Integer.toString(client.getPoints()).contains(text))
                results.add(client);
        return results;
    }

    public IRepository<Client> getClientRepository() { return clientRepository; }

    /**
     * Adds bonus points to clients whose dates of birth fall in a given date interval.
     * @param start is the lower boundary of the date interval.
     * @param end is the upper boundary of the date interval.
     * @param bonusPoints is the number of bonus points to be added.
     */

    public void addBonusPoints(LocalDate start, LocalDate end, int bonusPoints) {
        List<Client> currentClientCards = new ArrayList<>();
        List<Client> updatedClientCards;

        for (Client client : clientRepository.getAll())
            if (client.getBirthday().isAfter(start) && client.getBirthday().isBefore(end)) {
                Client current = new Client(client.getId(), client.getName(), client.getSurname(), client.getCnp(), client.getBirthday(), client.getRegistrationDay(), client.getPoints());
                currentClientCards.add(current);
                client.setPoints(client.getPoints() + bonusPoints);
                clientRepository.update(client);
            }
        updatedClientCards = clientRepository.getAll();
        undoableOperations.add(new ClientPointsUpdateOperation<>(clientRepository, currentClientCards, updatedClientCards));
        redoableOperations.clear();
    }

    public void undo() {
        if (!undoableOperations.isEmpty()) {
            UndoRedoOperation<Client> lastOperation = undoableOperations.pop();
            lastOperation.doUndo();
            redoableOperations.add(lastOperation);
        }
    }

    public void redo() {
        if (!redoableOperations.isEmpty()) {
            UndoRedoOperation<Client> lastOperation = redoableOperations.pop();
            lastOperation.doRedo();
            undoableOperations.add(lastOperation);
        }
    }

}
