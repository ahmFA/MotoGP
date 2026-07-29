package api.ahm.motogp.grandprix.application.port.in;

public record CreateGrandPrixCommand(
        String name,
        String circuitName,
        Integer countryId
) {
}
