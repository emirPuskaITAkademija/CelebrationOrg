package ba.organizuj.celebrationorg.api.privilege;

import ba.organizuj.celebrationorg.ejb.user.privilege.entity.Privilege;
import ba.organizuj.celebrationorg.ejb.user.privilege.service.PrivilegeService;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.net.URI;
import java.util.List;

@Path("/privileges")
public class PrivilegeRestService {

    @Inject
    private PrivilegeService privilegeService;

    // http zahtjev
    // header i body -> header je pun a body je prazan
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Privilege> getPrivileges(){
        List<Privilege> privilegeList = privilegeService.findAll();
        return privilegeList;
    }

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Privilege getPrivilege(@PathParam("id") int id){
        Privilege privilege = privilegeService.find(id);
        return privilege;
    }
    // http zahtjev
    // header i body -> header je pun a body je takođe pun
    @POST
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces(MediaType.TEXT_PLAIN)
    public Response addPrivilege(Privilege privilege){
       privilegeService.create(privilege);
       return Response
               .created(URI.create("http://localhost:8080/CelebrationOrg-1.0-SNAPSHOT/api/privileges/"+privilege.getId()))
               .build();
    }
}
