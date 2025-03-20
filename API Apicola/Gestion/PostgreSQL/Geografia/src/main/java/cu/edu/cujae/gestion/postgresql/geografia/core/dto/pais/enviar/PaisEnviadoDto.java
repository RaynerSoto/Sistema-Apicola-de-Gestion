package cu.edu.cujae.gestion.postgresql.geografia.core.dto.pais.enviar;

import cu.edu.cujae.gestion.postgresql.microservicepais.core.model.Pais;

public record PaisEnviadoDto(Long id, String codigo) {
    public static PaisEnviadoDto convertirPaisDtoSent(PaisDtoSent paisDtoSent) {
        return new PaisEnviadoDto(paisDtoSent.idPais(), paisDtoSent.sigla());
    }

    public static PaisEnviadoDto convertirPais(Pais pais) {
        return new PaisEnviadoDto(pais.getIdPais(), pais.getSigla());
    }

    @Override
    public String toString(){
        return String.format("{id=%d, codigo='%s'}",id,codigo);
    }
}
