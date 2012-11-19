/* **************************************************
 Copyright (c) 2012, University of Cambridge
 Neal Lathia, neal.lathia@cl.cam.ac.uk
 Kiran Rachuri, kiran.rachuri@cl.cam.ac.uk

This library was developed as part of the EPSRC Ubhave (Ubiquitous and
Social Computing for Positive Behaviour Change) Project. For more
information, please visit http://www.emotionsense.org

Permission to use, copy, modify, and/or distribute this software for any
purpose with or without fee is hereby granted, provided that the above
copyright notice and this permission notice appear in all copies.

THE SOFTWARE IS PROVIDED "AS IS" AND THE AUTHOR DISCLAIMS ALL WARRANTIES
WITH REGARD TO THIS SOFTWARE INCLUDING ALL IMPLIED WARRANTIES OF
MERCHANTABILITY AND FITNESS. IN NO EVENT SHALL THE AUTHOR BE LIABLE FOR ANY
SPECIAL, DIRECT, INDIRECT, OR CONSEQUENTIAL DAMAGES OR ANY DAMAGES
WHATSOEVER RESULTING FROM LOSS OF USE, DATA OR PROFITS, WHETHER IN AN
ACTION OF CONTRACT, NEGLIGENCE OR OTHER TORTIOUS ACTION, ARISING OUT OF OR
IN CONNECTION WITH THE USE OR PERFORMANCE OF THIS SOFTWARE.
 ************************************************** */

package com.ubhave.sensormanager.data.pushsensor;

import com.ubhave.sensormanager.config.SensorConfig;
import com.ubhave.sensormanager.data.SensorData;
import com.ubhave.sensormanager.sensors.SensorUtils;

public class ProximityData extends SensorData
{
	private static final float NEAR_DISTANCE = (float) 0.0;

	private float distance;
	private float maxRange;

	public ProximityData(long recvTimestamp, float distance, float maxRange, SensorConfig sensorConfig)
	{
		super(recvTimestamp, sensorConfig);
		this.distance = distance;
		this.maxRange = maxRange;
	}

	public boolean isNear()
	{
		if (distance == NEAR_DISTANCE)
		{
			return true;
		}
		return false;
	}
	
	public float getDistance()
	{
		return distance;
	}
	
	public float getMaxRange()
	{
		return maxRange;
	}

	public String getDataString()
	{
		return "Distance " + this.distance + " MaxRange " + maxRange;
	}

	public int getSensorType()
	{
		return SensorUtils.SENSOR_TYPE_PROXIMITY;
	}
}
