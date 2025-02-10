package PolicyNumber;
import java.util.UUID;

public class PolicyNumberGenerator {
	
	public static String generateUniquePolicyNumber() {
		String uniqueIdentifier = UUID.randomUUID().toString();
		
		uniqueIdentifier = uniqueIdentifier.replaceAll("-", "");
		
		return uniqueIdentifier.substring(0,10);
		
	}
	
}
