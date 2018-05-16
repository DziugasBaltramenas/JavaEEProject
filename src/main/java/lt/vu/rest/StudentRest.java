package lt.vu.rest;

import lt.vu.entities.Student;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@ApplicationScoped
@Path("/student")
@Produces(MediaType.APPLICATION_JSON)
public class StudentRest {

    @Inject private EntityManager em;

    @GET
    @Path("/{studentId}")
    public Student find(@PathParam("studentId") Integer studentId) {
        return em.find(Student.class, studentId);
    }

    @Path("/create")
    @POST
    @Transactional
    public Student create(@QueryParam("firstName") String name,
                                @QueryParam("lastName") String surname,
                                @QueryParam("nr") String nr) {
        Student student = new Student();
        student.setFirstName(name);
        student.setLastName(surname);
        student.setRegistrationNo(nr);
        em.persist(student);
        return student;
    }

    @Path("/update/{id}")
    @PUT @Transactional
    public Response update(@PathParam("id") Integer id,
                           @QueryParam("firstName") String name,
                           @QueryParam("lastName") String pwd,
                           @QueryParam("nr") String nr) {
        Student user = em.find(Student.class, id);
        if (user == null) {
            throw new IllegalArgumentException("user id "
                    + id + " not found");
        }
        user.setFirstName(name);
        user.setLastName(pwd);
        user.setRegistrationNo(nr);
        em.merge(user);
        return Response.ok(user).build();
    }

}
