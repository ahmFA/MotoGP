package api.ahm.motogp.rider.infrastructure.adapter.in.rest;

import api.ahm.motogp.rider.application.port.in.CreateRiderCommand;
import api.ahm.motogp.rider.application.port.in.FullUpdateRiderCommand;
import api.ahm.motogp.rider.domain.model.Rider;

import java.util.ArrayList;
import java.util.List;

final class RiderMapper {

    static CreateRiderCommand toCommand(CreateRiderRequest riderRequest){
        return new CreateRiderCommand(
                riderRequest.name(),
                riderRequest.number(),
                riderRequest.birthday(),
                riderRequest.countryId(),
                riderRequest.active()
        );
    }

    static FullUpdateRiderCommand toCommand(int id, PutRiderRequest riderRequest){
        return new FullUpdateRiderCommand(
                id,
                riderRequest.name(),
                riderRequest.number(),
                riderRequest.birthday(),
                riderRequest.countryId(),
                riderRequest.active()
        );
    }

    static RiderResponse toResponse(Rider rider){
        return new RiderResponse(
                rider.name(),
                rider.number(),
                rider.birthday(),
                rider.countryName()
        );
    }

    static List<RiderResponse> toResponse(List<Rider> riders){
        List<RiderResponse> responses = new ArrayList<>();
        for(var rider : riders){
            responses.add(toResponse(rider));
        }
        return responses;
    }
}
