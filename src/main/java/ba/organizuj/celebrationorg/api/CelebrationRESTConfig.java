package ba.organizuj.celebrationorg.api;

import jakarta.ws.rs.ApplicationPath;
import jakarta.ws.rs.core.Application;

/**
 * @ApplicationPath -> anotacija koja definira URL koji nas
 * vodi ka pozivima REST web servisa
 * <p>
 *     http://localhost:8080/CelebrationOrg-1.0-SNAPSHOT/api
 * </p>
 */
@ApplicationPath("/api")
public class CelebrationRESTConfig extends Application {
}
