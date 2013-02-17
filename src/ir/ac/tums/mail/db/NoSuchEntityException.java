package ir.ac.tums.mail.db;

public class NoSuchEntityException extends Exception
{
	public NoSuchEntityException(String message)
	{
		super(message);
	}
}
