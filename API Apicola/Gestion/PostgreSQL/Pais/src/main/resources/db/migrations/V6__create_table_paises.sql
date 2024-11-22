-- Table: public.paises

-- DROP TABLE IF EXISTS public.paises;

CREATE TABLE IF NOT EXISTS public.paises
(
    id_pais bigint NOT NULL DEFAULT nextval('pais_id_pais_seq'::regclass),
    nombre character varying(255) COLLATE pg_catalog."default" NOT NULL,
    siglas character varying(10) COLLATE pg_catalog."default" NOT NULL,
    poblacion bigint NOT NULL,
    geometria geometry,
    centroide geometry,
    nombre_continente character varying(50) COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT pais_pkey PRIMARY KEY (id_pais),
    CONSTRAINT geometria_unik UNIQUE (geometria),
    CONSTRAINT nombre_unik UNIQUE (nombre),
    CONSTRAINT siglas_unik UNIQUE (siglas),
    CONSTRAINT fk_pais_continente FOREIGN KEY (nombre_continente)
        REFERENCES public.continentes (nombre) MATCH SIMPLE
        ON UPDATE CASCADE
        ON DELETE SET NULL
        NOT VALID
)

TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.paises
    OWNER to postgres;