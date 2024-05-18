package co.edu.uco.pch.crosscutting.helpers;
import java.util.Random;
import java.util.UUID;

import org.springframework.boot.context.properties.bind.DefaultValue;

public class UUIDHelper {

	public static final String DEFAULT_UUID_STRING = "00000000-0000-0000-000000000000";
	
	private UUIDHelper() {
		super();
	}
	
	public static final UUID convertToUUID(final String uuidASString) {
		return UUID.fromString(uuidASString);
	}
	
	public static final UUID getDefault (final UUID value, final UUID defaultValue) {
		return ObjectHelper.getObjectHelper().getDefaultValue(value, defaultValue);
	}
	public static final UUID getDefault() {
		return convertToUUID(DEFAULT_UUID_STRING);
	}
	public static UUID generate() {
        return UUID.randomUUID();
    }
	
	public static UUID convertirStringaUUID(String uuidString) {
            return UUID.fromString(uuidString);        
    }
	
}