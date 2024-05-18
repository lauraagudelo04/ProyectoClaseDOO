package co.edu.uco.pch.business.usecase;

public interface UseCaseWithoutReturn<T> {
	void execute(T data);
	
	
}
