-- Table: public.continentes

-- DROP TABLE IF EXISTS public.continentes;

CREATE TABLE IF NOT EXISTS public.continentes
(
    id_continente bigint NOT NULL DEFAULT nextval('continentes_id_continente_seq'::regclass),
    nombre character varying(50) COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT continentes_pkey PRIMARY KEY (id_continente),
    CONSTRAINT continente_nombre_unik UNIQUE (nombre)
)

TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.continentes
    OWNER to postgres;