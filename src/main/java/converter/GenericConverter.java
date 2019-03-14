package converter;

import dto.ProfileDTO;
import entity.UserEntity;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public interface GenericConverter <D extends ProfileDTO, E extends UserEntity> {
	E createFrom(D dto);

	D createFrom(E entity);


	default List<D> createFromEntities(final Collection<E> entities) {
		return entities.stream()
				.map(this::createFrom)
				.collect(Collectors.toList());
	}

	default List<E> createFromDtos(final Collection<D> dtos) {
		return dtos.stream()
				.map(this::createFrom)
				.collect(Collectors.toList());
	}
}
