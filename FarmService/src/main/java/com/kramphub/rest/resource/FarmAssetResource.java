package com.kramphub.rest.resource;

import com.kramphub.db.FakeFarmDB;
import com.kramphub.rest.model.Alert;
import com.kramphub.rest.model.FarmAsset;
import org.apache.log4j.Logger;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

/**
 * Endpoint for the product search service
 *
 * @author miguel
 */
@Path("/myFarm")
public class FarmAssetResource {

    final static Logger logger = Logger.getLogger(FarmAssetResource.class);

    @POST
    @Path("/init")
    @Produces(MediaType.APPLICATION_JSON)
    public void init() {
        FakeFarmDB.initData();
    }

    @GET
    @Path("/machine/{machineId}")
    @Produces(MediaType.APPLICATION_JSON)
    public FarmAsset searchProducts(@PathParam("machineId") String machineId) {
        for (FarmAsset asset : FakeFarmDB.farmAssets) {
            if (asset.getMachineId().equals(machineId)) return asset;
        }
        return null;
    }

    @GET
    @Path("/machines")
    @Produces(MediaType.APPLICATION_JSON)
    public List<FarmAsset> getAllMachines() {
        return FakeFarmDB.farmAssets;
    }

    @POST
    @Path("/machine/{machineId}/alerts")
    @Produces(MediaType.APPLICATION_JSON)
    public void setAlertsForMachine(@PathParam("machineId") String machineId, List<Alert> alerts) {
        for (FarmAsset asset : FakeFarmDB.farmAssets) {
            if (asset.getMachineId().equals(machineId)) {
                asset.setAlerts(alerts);
            }
        }
    }

    @POST
    @Path("/machine/{machineId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response addOrUpdateFarmAsset(FarmAsset machine) {
        for (FarmAsset asset : FakeFarmDB.farmAssets) {
            if (asset.getMachineId().equals(machine.getMachineId())) {
                update(machine, asset);
                System.out.println("UPDATED: "+machine.getMachineId());
                return Response.status(Response.Status.CREATED).build();
            }
        }
        FakeFarmDB.farmAssets.add(machine);
        return Response.status(Response.Status.CREATED).build();
    }

    private void update(FarmAsset newMachine, FarmAsset old) {
        old.setOperationKilometers(newMachine.getOperationKilometers());
        old.setOperationHours(newMachine.getOperationHours());
        old.setAlerts(newMachine.getAlerts());
        old.setOrderHistory(newMachine.getOrderHistory());
        old.setMachineName(newMachine.getMachineName());
    }
}