package eu.heliopora.look4.commons.persistence;

import java.nio.ByteBuffer;
import java.util.UUID;

import org.apache.commons.codec.binary.Base64;

public class IdGenerator {
		
	public static String createId() {
		UUID uuid = UUID.randomUUID();
		return encode(uuid);
	}
	
	private static String encode(UUID uuid) {
        ByteBuffer buffer = ByteBuffer.wrap(new byte[16]);
        buffer.putLong(uuid.getMostSignificantBits());
        buffer.putLong(uuid.getLeastSignificantBits());
        return Base64.encodeBase64URLSafeString(buffer.array());
}

}
