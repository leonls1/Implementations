package LeonLederhos.improvements.model.dto.response;

import java.time.LocalDate;

import com.noCountry.artAuction.model.enums.ArtCategory;

public record ArtWorkResponse(String artWorkName,

                              String artistName,

                              String artWorkDescription,

                              ArtCategory category,

                              LocalDate creationDate,

                              String urlImage,
                              UserResponse user) {
}
