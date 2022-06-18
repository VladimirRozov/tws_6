/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ru.itmo;

import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import ru.itmo.errors.*;

@Path("/loyalty")
@Produces({MediaType.APPLICATION_JSON})

public class LoyaltyResource {
    @GET
    public ArrayList<Loyalty> getAll() {
        return (ArrayList<Loyalty>) new PostgreSQLDAO().getAll();
    }
    
    @POST
    public String createLoyalty(
            @QueryParam("spbso") String spbso,
            @QueryParam("name") String name,
            @QueryParam("brigade") String brigade,
            @QueryParam("event") String event,
            @QueryParam("cash") String cash) throws CastToIntException, EmptyFieldException {
            String status = "-1";

        if (name != null && !name.trim().isEmpty() &&
                spbso != null && !spbso.trim().isEmpty() &&
                brigade != null && !brigade.trim().isEmpty() &&
                cash != null && !cash.trim().isEmpty() &&
                event != null && !event.trim().isEmpty()) {

            try {
                Integer.parseInt(spbso.trim());
                Integer.parseInt(cash.trim());

                    status = new PostgreSQLDAO().createLoyalty(spbso, name, brigade, event, cash);

            } catch (NumberFormatException ex) {
                throw new CastToIntException("An error occurred when trying to convert 'cash' and `spbso_id` to integers. ");
            }

        } else {
            throw EmptyFieldException.DEFAULT_INSTANCE;
        }

        return status;    }

    @DELETE
    public String deleteLoyalty(@QueryParam("rowId") String rowId) throws RowIsNotExistsException, CastToIntException, EmptyFieldException {
        String status;
        if (rowId != null && !rowId.trim().isEmpty()) {
            try {
                Integer.parseInt(rowId.trim());
                status = new PostgreSQLDAO().deleteLoyalty(rowId);
                if (status.equals("0")) {
                    throw RowIsNotExistsException.DEFAULT_INSTANCE;
                }
            } catch (NumberFormatException ex) {
                throw new CastToIntException("An error occurred when trying to convert 'rowId' to integer. ");
            }
        } else {
            throw EmptyFieldException.DEFAULT_INSTANCE;
        }
        return status;
    }


    @PUT
    public String updateLoyalty(
            @QueryParam("rowId") String rowId,
            @QueryParam("spbso") String spbso,
            @QueryParam("name") String name,
            @QueryParam("brigade") String brigade,
            @QueryParam("event") String event,
            @QueryParam("cash") String cash) throws EmptyFieldException, CastToIntException, RowIsNotExistsException {

        String status;
        List<String> updateArgs = new ArrayList<>();

        if (rowId != null && !rowId.trim().isEmpty()) {
            if ((name != null && !name.trim().isEmpty()) ||
                    (spbso != null && !spbso.trim().isEmpty()) ||
                    (brigade != null && !brigade.trim().isEmpty()) ||
                    (event != null && !event.trim().isEmpty()) ||
                    (cash != null && !cash.trim().isEmpty())) {

                try {
                    Integer.parseInt(rowId.trim());
                    if (name != null && !name.trim().isEmpty()) updateArgs.add("name = '" + name + "'");
                    if (brigade != null && !brigade.trim().isEmpty()) updateArgs.add("brigade = '" + brigade + "'");
                    if (event != null && !event.trim().isEmpty()) updateArgs.add("event = '" + event + "'");
                    try {
                        if (cash != null && !cash.trim().isEmpty()) {
                            Integer.parseInt(cash.trim());
                            updateArgs.add("cash = '" + cash + "'");
                        }
                        if (spbso != null && !spbso.trim().isEmpty()) {
                            Integer.parseInt(spbso.trim());
                            updateArgs.add("spbso = '" + spbso + "'");
                        }
                    } catch (NumberFormatException e) {
                        throw new CastToIntException("An error occurred when trying to convert " +
                            "'cash' or 'spbsoId' to integer. ");
                    }
                } catch (NumberFormatException e) {
                    throw new CastToIntException("An error occurred when trying to convert 'rowId' to integer. ");
                }
            } else {
                throw EmptyFieldException.DEFAULT_INSTANCE;
            }
        } else {
            throw new EmptyFieldException("rowID cannot be empty!");
        }
        

        status = new PostgreSQLDAO().updateLoyalty(rowId, updateArgs);
        if (status.equals("0")) {
            throw RowIsNotExistsException.DEFAULT_INSTANCE;
        }

        return status;
    }
}
