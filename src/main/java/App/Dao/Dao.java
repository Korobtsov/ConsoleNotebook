package App.Dao;

public interface Dao<E> {
    E create(E object);
    E getByName(String name);
    E getByEmail(String email);
}
