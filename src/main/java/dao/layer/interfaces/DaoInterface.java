package dao.layer.interfaces;

public interface DaoInterface<T> {
    T create(T t);
    T getById(int id);
    void delete(int id);
}
