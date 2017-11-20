package us.inal.boilerplatewebapp.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import us.inal.boilerplatewebapp.Status;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by alpereninal on 20/11/17.
 */
@RestController
public class HealthController {


    Map<String, Status> servers = new HashMap<>();

    @GetMapping("/servers/{serverId}/heartbeat")
    public Map<String, Status> heartbeatListener(@PathVariable String serverId){
        servers.put(serverId, Status.OK);
        return servers;
    }

    @GetMapping("servers/health/check")
    public Map<String, Status> checkStatus() {
        return servers;
    }

}
