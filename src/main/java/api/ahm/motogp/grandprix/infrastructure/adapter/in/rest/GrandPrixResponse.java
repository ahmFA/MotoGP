package api.ahm.motogp.grandprix.infrastructure.adapter.in.rest;

public record GrandPrixResponse(
        Integer id,
        String name,
        String circuitName,
        Integer countryId,
        String countryName
) {
}
