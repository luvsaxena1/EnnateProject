package io.ennate.repository;

import java.io.Serializable;
import java.util.List;

import org.mongodb.morphia.Key;
import org.mongodb.morphia.query.UpdateOperations;
import org.mongodb.morphia.query.UpdateResults;
import org.springframework.stereotype.Component;

import com.mongodb.WriteResult;

@Component
public interface CrudRepository<T, ID extends Serializable> {
	public Key<T> create(T entity);

	public T read(ID id);

	public List<T> read();

	public UpdateResults update(T entity, UpdateOperations<T> operations);

	public WriteResult delete(T entity);

	public UpdateOperations<T> createOperations();
}
