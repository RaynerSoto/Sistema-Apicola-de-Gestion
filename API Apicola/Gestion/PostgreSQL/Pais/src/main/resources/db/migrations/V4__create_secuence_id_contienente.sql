-- SEQUENCE: public.secuence_id_continente

-- DROP SEQUENCE IF EXISTS public.secuence_id_continente;

CREATE SEQUENCE IF NOT EXISTS public.secuence_id_continente
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 9223372036854775807
    CACHE 1;

ALTER SEQUENCE public.secuence_id_continente
    OWNER TO postgres;