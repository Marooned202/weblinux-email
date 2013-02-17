package ir.ac.tums.mail.db;

import ir.ac.tums.mail.db.CreateException;

public class DuplicateKeyException extends CreateException
{
	public DuplicateKeyException(String message)
	{
		super(message);
	}
}
