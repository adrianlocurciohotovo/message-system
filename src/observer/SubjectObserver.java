package observer;

public interface SubjectObserver<T> {
	void attach(T observer);
	void detach(T observer);
	void notifyChange();
}
