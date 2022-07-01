
package rs.fon.silab.converter;

/**
 * 
 * @author goran
 *
 * @param <D> - Generic parameter representing dto class that will be converted to entity
 * @param <E> - Generic parameter representing entity class that will be converted to dto
 */
public interface Converter<D,E> {
	
	D toDto(E e);
	
	E toEntity(D d);

}