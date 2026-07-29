package api.ahm.motogp.grandprix.application.port.in;

public record FullUpdateGrandPrixCommand(
        Integer id,
        String name,
        String circuitName,
        Integer countryId
) {
}
