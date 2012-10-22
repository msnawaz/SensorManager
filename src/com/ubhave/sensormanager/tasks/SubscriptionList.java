package com.ubhave.sensormanager.tasks;

import java.util.Random;

import android.util.SparseArray;

import com.ubhave.sensormanager.ESException;
import com.ubhave.sensormanager.logs.ESLogger;

public class SubscriptionList
{
	private static String TAG = "SubscriptionList";
	
	private final SparseArray<Subscription> subscriptionMap;
	private final Random keyGenerator;

	public SubscriptionList()
	{
		subscriptionMap = new SparseArray<Subscription>();
		keyGenerator = new Random();
	}

	public int registerSubscription(Subscription s) throws ESException
	{
		if (!s.register()) // subscription already exists
		{
			ESLogger.log(TAG, "registerSubscription() subscription already exists for task: " + s.getTask().getSensorType() + " listener: " + s.getListener());
			for (int i = 0; i < subscriptionMap.size(); i++)
			{
				int subscriptionId = subscriptionMap.keyAt(i);
				Subscription subscription = subscriptionMap.get(subscriptionId);
				if (subscription.equals(s))
				{
					return subscriptionId;
				}
			}
			throw new ESException(ESException.INVALID_STATE, "Registered Subscription not found.");
		}
		else
		{
			int subscriptionId = randomKey();
			subscriptionMap.append(subscriptionId, s);
			ESLogger.log(TAG, "registerSubscription() new subscription created for task: " + s.getTask().getSensorType() + " listener: " + s.getListener());
			return subscriptionId;
		}
	}

	public Subscription removeSubscription(int subscriptionId)
	{
		Subscription s = subscriptionMap.get(subscriptionId);
		subscriptionMap.delete(subscriptionId);
		ESLogger.log(TAG, "registerSubscription() deleted subscription created for task: " + s.getTask().getSensorType() + " listener: " + s.getListener());
		return s;
	}

	private int randomKey() throws ESException
	{
		int subscriptionId = keyGenerator.nextInt();
		int loopCount = 0;
		while (subscriptionMap.get(subscriptionId) != null)
		{
			if (loopCount > 1000)
				throw new ESException(ESException.INVALID_STATE, "Listener map >1000 key conflicts.");
			subscriptionId = keyGenerator.nextInt();
			loopCount++;
		}
		return subscriptionId;
	}
}