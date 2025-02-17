--
-- PostgreSQL database dump
--

-- Dumped from database version 12.20
-- Dumped by pg_dump version 16.4

-- Started on 2025-01-31 14:16:23

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

--
-- TOC entry 10 (class 2615 OID 50304)
-- Name: pgagent; Type: SCHEMA; Schema: -; Owner: postgres
--

CREATE SCHEMA pgagent;


ALTER SCHEMA pgagent OWNER TO postgres;

--
-- TOC entry 4120 (class 0 OID 0)
-- Dependencies: 10
-- Name: SCHEMA pgagent; Type: COMMENT; Schema: -; Owner: postgres
--

COMMENT ON SCHEMA pgagent IS 'pgAgent system tables';


--
-- TOC entry 8 (class 2615 OID 2200)
-- Name: public; Type: SCHEMA; Schema: -; Owner: postgres
--

-- *not* creating schema, since initdb creates it


ALTER SCHEMA public OWNER TO postgres;

--
-- TOC entry 3 (class 3079 OID 50305)
-- Name: pgagent; Type: EXTENSION; Schema: -; Owner: -
--

CREATE EXTENSION IF NOT EXISTS pgagent WITH SCHEMA pgagent;


--
-- TOC entry 4122 (class 0 OID 0)
-- Dependencies: 3
-- Name: EXTENSION pgagent; Type: COMMENT; Schema: -; Owner: 
--

COMMENT ON EXTENSION pgagent IS 'A PostgreSQL job scheduler';


--
-- TOC entry 2 (class 3079 OID 49173)
-- Name: postgis; Type: EXTENSION; Schema: -; Owner: -
--

CREATE EXTENSION IF NOT EXISTS postgis WITH SCHEMA public;


--
-- TOC entry 4123 (class 0 OID 0)
-- Dependencies: 2
-- Name: EXTENSION postgis; Type: COMMENT; Schema: -; Owner: 
--

COMMENT ON EXTENSION postgis IS 'PostGIS geometry and geography spatial types and functions';


SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- TOC entry 248 (class 1259 OID 75023)
-- Name: apiarios; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.apiarios (
    id_apiario bigint NOT NULL,
    identificador character varying(255) NOT NULL,
    nombre character varying(255) NOT NULL,
    direccion character varying(255) NOT NULL,
    geometry public.geometry
);


ALTER TABLE public.apiarios OWNER TO postgres;

--
-- TOC entry 247 (class 1259 OID 75021)
-- Name: apiarios_id_apiario_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.apiarios_id_apiario_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.apiarios_id_apiario_seq OWNER TO postgres;

--
-- TOC entry 4124 (class 0 OID 0)
-- Dependencies: 247
-- Name: apiarios_id_apiario_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.apiarios_id_apiario_seq OWNED BY public.apiarios.id_apiario;


--
-- TOC entry 250 (class 1259 OID 75034)
-- Name: colmenas; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.colmenas (
    id_colmena bigint NOT NULL,
    identificador character varying NOT NULL,
    id_apiario bigint NOT NULL,
    cantidad_reinas bigint NOT NULL
);


ALTER TABLE public.colmenas OWNER TO postgres;

--
-- TOC entry 249 (class 1259 OID 75032)
-- Name: colmenas_id_colmena_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.colmenas_id_colmena_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.colmenas_id_colmena_seq OWNER TO postgres;

--
-- TOC entry 4125 (class 0 OID 0)
-- Dependencies: 249
-- Name: colmenas_id_colmena_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.colmenas_id_colmena_seq OWNED BY public.colmenas.id_colmena;


--
-- TOC entry 212 (class 1259 OID 50276)
-- Name: continentes; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.continentes (
    id_continente bigint NOT NULL,
    nombre character varying(50) NOT NULL
);


ALTER TABLE public.continentes OWNER TO postgres;

--
-- TOC entry 213 (class 1259 OID 50287)
-- Name: continentes_id_continente_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.continentes_id_continente_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.continentes_id_continente_seq OWNER TO postgres;

--
-- TOC entry 4126 (class 0 OID 0)
-- Dependencies: 213
-- Name: continentes_id_continente_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.continentes_id_continente_seq OWNED BY public.continentes.id_continente;


--
-- TOC entry 244 (class 1259 OID 74978)
-- Name: cooperativas; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.cooperativas (
    id bigint NOT NULL,
    nombre character varying(255) NOT NULL,
    direccion_particular character varying(255) NOT NULL,
    geometry public.geometry,
    tipo bigint
);


ALTER TABLE public.cooperativas OWNER TO postgres;

--
-- TOC entry 243 (class 1259 OID 74976)
-- Name: cooperativas_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.cooperativas_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.cooperativas_id_seq OWNER TO postgres;

--
-- TOC entry 4127 (class 0 OID 0)
-- Dependencies: 243
-- Name: cooperativas_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.cooperativas_id_seq OWNED BY public.cooperativas.id;


--
-- TOC entry 230 (class 1259 OID 58498)
-- Name: estados; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.estados (
    id_estado bigint NOT NULL,
    nombre character varying(250) NOT NULL,
    poblacion bigint DEFAULT 0 NOT NULL,
    geometria public.geometry,
    centroide public.geometry,
    id_pais bigint NOT NULL,
    codigo character varying(50) NOT NULL,
    areakm2 double precision DEFAULT 0 NOT NULL
);


ALTER TABLE public.estados OWNER TO postgres;

--
-- TOC entry 229 (class 1259 OID 58496)
-- Name: estados_id_estados_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.estados_id_estados_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.estados_id_estados_seq OWNER TO postgres;

--
-- TOC entry 4128 (class 0 OID 0)
-- Dependencies: 229
-- Name: estados_id_estados_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.estados_id_estados_seq OWNED BY public.estados.id_estado;


--
-- TOC entry 231 (class 1259 OID 58520)
-- Name: municipios; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.municipios (
    id_municipio bigint NOT NULL,
    nombre character varying(100) NOT NULL,
    siglas character varying(10) NOT NULL,
    poblacion bigint DEFAULT 0 NOT NULL,
    geometria public.geometry,
    centroide public.geometry,
    id_estado bigint NOT NULL,
    codigo character varying(50) NOT NULL,
    areakm2 double precision DEFAULT 0 NOT NULL
);


ALTER TABLE public.municipios OWNER TO postgres;

--
-- TOC entry 232 (class 1259 OID 58523)
-- Name: municipios_id_municipio_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.municipios_id_municipio_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.municipios_id_municipio_seq OWNER TO postgres;

--
-- TOC entry 4129 (class 0 OID 0)
-- Dependencies: 232
-- Name: municipios_id_municipio_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.municipios_id_municipio_seq OWNED BY public.municipios.id_municipio;


--
-- TOC entry 211 (class 1259 OID 50261)
-- Name: paises; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.paises (
    id_pais bigint NOT NULL,
    nombre character varying(100) NOT NULL,
    sigla character varying(10) NOT NULL,
    poblacion bigint DEFAULT 0 NOT NULL,
    geometria public.geometry,
    centroide public.geometry,
    nombre_continente character varying(50) NOT NULL,
    areakm2 double precision DEFAULT 0 NOT NULL
);


ALTER TABLE public.paises OWNER TO postgres;

--
-- TOC entry 210 (class 1259 OID 50259)
-- Name: pais_id_pais_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.pais_id_pais_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.pais_id_pais_seq OWNER TO postgres;

--
-- TOC entry 4130 (class 0 OID 0)
-- Dependencies: 210
-- Name: pais_id_pais_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.pais_id_pais_seq OWNED BY public.paises.id_pais;


--
-- TOC entry 239 (class 1259 OID 74941)
-- Name: permisos; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.permisos (
    id_permiso bigint NOT NULL,
    nombre_permiso character varying(255) NOT NULL,
    descripcion character varying(255) NOT NULL
);


ALTER TABLE public.permisos OWNER TO postgres;

--
-- TOC entry 238 (class 1259 OID 74939)
-- Name: permisos_id_permiso_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.permisos_id_permiso_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.permisos_id_permiso_seq OWNER TO postgres;

--
-- TOC entry 4131 (class 0 OID 0)
-- Dependencies: 238
-- Name: permisos_id_permiso_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.permisos_id_permiso_seq OWNED BY public.permisos.id_permiso;


--
-- TOC entry 255 (class 1259 OID 75081)
-- Name: produccion; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.produccion (
    id_produccion bigint NOT NULL,
    id_colmena bigint NOT NULL,
    fecha timestamp with time zone NOT NULL,
    cantidad_miel double precision NOT NULL,
    cantidad_cera double precision NOT NULL,
    cantidad_propoleo double precision NOT NULL,
    cantidad_polen double precision NOT NULL
);


ALTER TABLE public.produccion OWNER TO postgres;

--
-- TOC entry 254 (class 1259 OID 75079)
-- Name: produccion_id_produccion_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.produccion_id_produccion_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.produccion_id_produccion_seq OWNER TO postgres;

--
-- TOC entry 4132 (class 0 OID 0)
-- Dependencies: 254
-- Name: produccion_id_produccion_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.produccion_id_produccion_seq OWNED BY public.produccion.id_produccion;


--
-- TOC entry 242 (class 1259 OID 74967)
-- Name: registros; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.registros (
    id_registro bigint NOT NULL,
    funcion character varying(255) NOT NULL,
    resultado character varying(255) NOT NULL,
    fecha timestamp without time zone NOT NULL,
    usuario_id bigint
);


ALTER TABLE public.registros OWNER TO postgres;

--
-- TOC entry 241 (class 1259 OID 74965)
-- Name: registros_id_registro_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.registros_id_registro_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.registros_id_registro_seq OWNER TO postgres;

--
-- TOC entry 4133 (class 0 OID 0)
-- Dependencies: 241
-- Name: registros_id_registro_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.registros_id_registro_seq OWNED BY public.registros.id_registro;


--
-- TOC entry 251 (class 1259 OID 75048)
-- Name: relacion_apiarios_apiculores; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.relacion_apiarios_apiculores (
    id_apiario bigint NOT NULL,
    id_apicultor bigint NOT NULL
);


ALTER TABLE public.relacion_apiarios_apiculores OWNER TO postgres;

--
-- TOC entry 245 (class 1259 OID 74987)
-- Name: relacion_cooperativas_administradores; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.relacion_cooperativas_administradores (
    id_administrador bigint NOT NULL,
    id_cooperativa bigint NOT NULL
);


ALTER TABLE public.relacion_cooperativas_administradores OWNER TO postgres;

--
-- TOC entry 246 (class 1259 OID 75002)
-- Name: relacion_cooperativas_apicultores; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.relacion_cooperativas_apicultores (
    id_apicultor bigint NOT NULL,
    id_cooperativa bigint NOT NULL
);


ALTER TABLE public.relacion_cooperativas_apicultores OWNER TO postgres;

--
-- TOC entry 240 (class 1259 OID 74950)
-- Name: relacion_permisos_roles; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.relacion_permisos_roles (
    id_rol bigint NOT NULL,
    id_permiso bigint NOT NULL
);


ALTER TABLE public.relacion_permisos_roles OWNER TO postgres;

--
-- TOC entry 237 (class 1259 OID 74909)
-- Name: relacion_usuarios_roles; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.relacion_usuarios_roles (
    id_usuario bigint NOT NULL,
    id_rol bigint NOT NULL
);


ALTER TABLE public.relacion_usuarios_roles OWNER TO postgres;

--
-- TOC entry 236 (class 1259 OID 74899)
-- Name: roles; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.roles (
    id_rol bigint NOT NULL,
    nombre character varying(100) NOT NULL,
    descripcion character varying(255) NOT NULL
);


ALTER TABLE public.roles OWNER TO postgres;

--
-- TOC entry 235 (class 1259 OID 74897)
-- Name: roles_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.roles_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.roles_id_seq OWNER TO postgres;

--
-- TOC entry 4134 (class 0 OID 0)
-- Dependencies: 235
-- Name: roles_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.roles_id_seq OWNED BY public.roles.id_rol;


--
-- TOC entry 253 (class 1259 OID 75065)
-- Name: tipo_cooperativa; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.tipo_cooperativa (
    id_clasificacion_cooperativa bigint NOT NULL,
    nombre character varying(255) NOT NULL,
    descripcion character varying(255) NOT NULL
);


ALTER TABLE public.tipo_cooperativa OWNER TO postgres;

--
-- TOC entry 252 (class 1259 OID 75063)
-- Name: tipo_cooperativa_id_clasificacion_cooperativa_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.tipo_cooperativa_id_clasificacion_cooperativa_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.tipo_cooperativa_id_clasificacion_cooperativa_seq OWNER TO postgres;

--
-- TOC entry 4135 (class 0 OID 0)
-- Dependencies: 252
-- Name: tipo_cooperativa_id_clasificacion_cooperativa_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.tipo_cooperativa_id_clasificacion_cooperativa_seq OWNED BY public.tipo_cooperativa.id_clasificacion_cooperativa;


--
-- TOC entry 234 (class 1259 OID 74882)
-- Name: usuarios; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.usuarios (
    id_usuario bigint NOT NULL,
    identificacion character varying(50) NOT NULL,
    nombre character varying(255) NOT NULL,
    username character varying(255) NOT NULL,
    password character varying(255) NOT NULL,
    email character varying(100) NOT NULL,
    id_municipio bigint NOT NULL,
    id_estado bigint NOT NULL,
    id_pais bigint NOT NULL,
    direccion_particular character varying(255),
    geo_posicion point,
    telefono character varying(50) NOT NULL
);


ALTER TABLE public.usuarios OWNER TO postgres;

--
-- TOC entry 233 (class 1259 OID 74880)
-- Name: users_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.users_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.users_id_seq OWNER TO postgres;

--
-- TOC entry 4136 (class 0 OID 0)
-- Dependencies: 233
-- Name: users_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.users_id_seq OWNED BY public.usuarios.id_usuario;


--
-- TOC entry 3827 (class 2604 OID 75026)
-- Name: apiarios id_apiario; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.apiarios ALTER COLUMN id_apiario SET DEFAULT nextval('public.apiarios_id_apiario_seq'::regclass);


--
-- TOC entry 3828 (class 2604 OID 75037)
-- Name: colmenas id_colmena; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.colmenas ALTER COLUMN id_colmena SET DEFAULT nextval('public.colmenas_id_colmena_seq'::regclass);


--
-- TOC entry 3785 (class 2604 OID 50289)
-- Name: continentes id_continente; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.continentes ALTER COLUMN id_continente SET DEFAULT nextval('public.continentes_id_continente_seq'::regclass);


--
-- TOC entry 3826 (class 2604 OID 74981)
-- Name: cooperativas id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.cooperativas ALTER COLUMN id SET DEFAULT nextval('public.cooperativas_id_seq'::regclass);


--
-- TOC entry 3816 (class 2604 OID 58501)
-- Name: estados id_estado; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.estados ALTER COLUMN id_estado SET DEFAULT nextval('public.estados_id_estados_seq'::regclass);


--
-- TOC entry 3819 (class 2604 OID 58525)
-- Name: municipios id_municipio; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.municipios ALTER COLUMN id_municipio SET DEFAULT nextval('public.municipios_id_municipio_seq'::regclass);


--
-- TOC entry 3782 (class 2604 OID 50264)
-- Name: paises id_pais; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.paises ALTER COLUMN id_pais SET DEFAULT nextval('public.pais_id_pais_seq'::regclass);


--
-- TOC entry 3824 (class 2604 OID 74944)
-- Name: permisos id_permiso; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.permisos ALTER COLUMN id_permiso SET DEFAULT nextval('public.permisos_id_permiso_seq'::regclass);


--
-- TOC entry 3830 (class 2604 OID 75084)
-- Name: produccion id_produccion; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.produccion ALTER COLUMN id_produccion SET DEFAULT nextval('public.produccion_id_produccion_seq'::regclass);


--
-- TOC entry 3825 (class 2604 OID 74970)
-- Name: registros id_registro; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.registros ALTER COLUMN id_registro SET DEFAULT nextval('public.registros_id_registro_seq'::regclass);


--
-- TOC entry 3823 (class 2604 OID 74902)
-- Name: roles id_rol; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.roles ALTER COLUMN id_rol SET DEFAULT nextval('public.roles_id_seq'::regclass);


--
-- TOC entry 3829 (class 2604 OID 75068)
-- Name: tipo_cooperativa id_clasificacion_cooperativa; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.tipo_cooperativa ALTER COLUMN id_clasificacion_cooperativa SET DEFAULT nextval('public.tipo_cooperativa_id_clasificacion_cooperativa_seq'::regclass);


--
-- TOC entry 3822 (class 2604 OID 74885)
-- Name: usuarios id_usuario; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.usuarios ALTER COLUMN id_usuario SET DEFAULT nextval('public.users_id_seq'::regclass);


--
-- TOC entry 3774 (class 0 OID 50306)
-- Dependencies: 214
-- Data for Name: pga_jobagent; Type: TABLE DATA; Schema: pgagent; Owner: postgres
--

COPY pgagent.pga_jobagent (jagpid, jaglogintime, jagstation) FROM stdin;
\.


--
-- TOC entry 3775 (class 0 OID 50317)
-- Dependencies: 216
-- Data for Name: pga_jobclass; Type: TABLE DATA; Schema: pgagent; Owner: postgres
--

COPY pgagent.pga_jobclass (jclid, jclname) FROM stdin;
\.


--
-- TOC entry 3776 (class 0 OID 50329)
-- Dependencies: 218
-- Data for Name: pga_job; Type: TABLE DATA; Schema: pgagent; Owner: postgres
--

COPY pgagent.pga_job (jobid, jobjclid, jobname, jobdesc, jobhostagent, jobenabled, jobcreated, jobchanged, jobagentid, jobnextrun, joblastrun) FROM stdin;
\.


--
-- TOC entry 3778 (class 0 OID 50381)
-- Dependencies: 222
-- Data for Name: pga_schedule; Type: TABLE DATA; Schema: pgagent; Owner: postgres
--

COPY pgagent.pga_schedule (jscid, jscjobid, jscname, jscdesc, jscenabled, jscstart, jscend, jscminutes, jschours, jscweekdays, jscmonthdays, jscmonths) FROM stdin;
\.


--
-- TOC entry 3779 (class 0 OID 50411)
-- Dependencies: 224
-- Data for Name: pga_exception; Type: TABLE DATA; Schema: pgagent; Owner: postgres
--

COPY pgagent.pga_exception (jexid, jexscid, jexdate, jextime) FROM stdin;
\.


--
-- TOC entry 3780 (class 0 OID 50426)
-- Dependencies: 226
-- Data for Name: pga_joblog; Type: TABLE DATA; Schema: pgagent; Owner: postgres
--

COPY pgagent.pga_joblog (jlgid, jlgjobid, jlgstatus, jlgstart, jlgduration) FROM stdin;
\.


--
-- TOC entry 3777 (class 0 OID 50355)
-- Dependencies: 220
-- Data for Name: pga_jobstep; Type: TABLE DATA; Schema: pgagent; Owner: postgres
--

COPY pgagent.pga_jobstep (jstid, jstjobid, jstname, jstdesc, jstenabled, jstkind, jstcode, jstconnstr, jstdbname, jstonerror, jscnextrun) FROM stdin;
\.


--
-- TOC entry 3781 (class 0 OID 50443)
-- Dependencies: 228
-- Data for Name: pga_jobsteplog; Type: TABLE DATA; Schema: pgagent; Owner: postgres
--

COPY pgagent.pga_jobsteplog (jslid, jsljlgid, jsljstid, jslstatus, jslresult, jslstart, jslduration, jsloutput) FROM stdin;
\.


--
-- TOC entry 4107 (class 0 OID 75023)
-- Dependencies: 248
-- Data for Name: apiarios; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.apiarios (id_apiario, identificador, nombre, direccion, geometry) FROM stdin;
\.


--
-- TOC entry 4109 (class 0 OID 75034)
-- Dependencies: 250
-- Data for Name: colmenas; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.colmenas (id_colmena, identificador, id_apiario, cantidad_reinas) FROM stdin;
\.


--
-- TOC entry 4086 (class 0 OID 50276)
-- Dependencies: 212
-- Data for Name: continentes; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.continentes (id_continente, nombre) FROM stdin;
1	América
2	África
3	Europa
4	Asia
5	Oceanía
\.


--
-- TOC entry 4103 (class 0 OID 74978)
-- Dependencies: 244
-- Data for Name: cooperativas; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.cooperativas (id, nombre, direccion_particular, geometry, tipo) FROM stdin;
\.


--
-- TOC entry 4089 (class 0 OID 58498)
-- Dependencies: 230
-- Data for Name: estados; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.estados (id_estado, nombre, poblacion, geometria, centroide, id_pais, codigo, areakm2) FROM stdin;
\.


--
-- TOC entry 4090 (class 0 OID 58520)
-- Dependencies: 231
-- Data for Name: municipios; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.municipios (id_municipio, nombre, siglas, poblacion, geometria, centroide, id_estado, codigo, areakm2) FROM stdin;
\.


--
-- TOC entry 4085 (class 0 OID 50261)
-- Dependencies: 211
-- Data for Name: paises; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.paises (id_pais, nombre, sigla, poblacion, geometria, centroide, nombre_continente, areakm2) FROM stdin;
52	México	MX	100	\N	\N	América	0
3	Cuba	CU	0	\N	\N	América	0
\.


--
-- TOC entry 4098 (class 0 OID 74941)
-- Dependencies: 239
-- Data for Name: permisos; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.permisos (id_permiso, nombre_permiso, descripcion) FROM stdin;
\.


--
-- TOC entry 4114 (class 0 OID 75081)
-- Dependencies: 255
-- Data for Name: produccion; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.produccion (id_produccion, id_colmena, fecha, cantidad_miel, cantidad_cera, cantidad_propoleo, cantidad_polen) FROM stdin;
\.


--
-- TOC entry 4101 (class 0 OID 74967)
-- Dependencies: 242
-- Data for Name: registros; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.registros (id_registro, funcion, resultado, fecha, usuario_id) FROM stdin;
\.


--
-- TOC entry 4110 (class 0 OID 75048)
-- Dependencies: 251
-- Data for Name: relacion_apiarios_apiculores; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.relacion_apiarios_apiculores (id_apiario, id_apicultor) FROM stdin;
\.


--
-- TOC entry 4104 (class 0 OID 74987)
-- Dependencies: 245
-- Data for Name: relacion_cooperativas_administradores; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.relacion_cooperativas_administradores (id_administrador, id_cooperativa) FROM stdin;
\.


--
-- TOC entry 4105 (class 0 OID 75002)
-- Dependencies: 246
-- Data for Name: relacion_cooperativas_apicultores; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.relacion_cooperativas_apicultores (id_apicultor, id_cooperativa) FROM stdin;
\.


--
-- TOC entry 4099 (class 0 OID 74950)
-- Dependencies: 240
-- Data for Name: relacion_permisos_roles; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.relacion_permisos_roles (id_rol, id_permiso) FROM stdin;
\.


--
-- TOC entry 4096 (class 0 OID 74909)
-- Dependencies: 237
-- Data for Name: relacion_usuarios_roles; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.relacion_usuarios_roles (id_usuario, id_rol) FROM stdin;
\.


--
-- TOC entry 4095 (class 0 OID 74899)
-- Dependencies: 236
-- Data for Name: roles; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.roles (id_rol, nombre, descripcion) FROM stdin;
\.


--
-- TOC entry 3773 (class 0 OID 49490)
-- Dependencies: 206
-- Data for Name: spatial_ref_sys; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.spatial_ref_sys (srid, auth_name, auth_srid, srtext, proj4text) FROM stdin;
\.


--
-- TOC entry 4112 (class 0 OID 75065)
-- Dependencies: 253
-- Data for Name: tipo_cooperativa; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.tipo_cooperativa (id_clasificacion_cooperativa, nombre, descripcion) FROM stdin;
\.


--
-- TOC entry 4093 (class 0 OID 74882)
-- Dependencies: 234
-- Data for Name: usuarios; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.usuarios (id_usuario, identificacion, nombre, username, password, email, id_municipio, id_estado, id_pais, direccion_particular, geo_posicion, telefono) FROM stdin;
\.


--
-- TOC entry 4137 (class 0 OID 0)
-- Dependencies: 247
-- Name: apiarios_id_apiario_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.apiarios_id_apiario_seq', 1, false);


--
-- TOC entry 4138 (class 0 OID 0)
-- Dependencies: 249
-- Name: colmenas_id_colmena_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.colmenas_id_colmena_seq', 1, false);


--
-- TOC entry 4139 (class 0 OID 0)
-- Dependencies: 213
-- Name: continentes_id_continente_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.continentes_id_continente_seq', 5, true);


--
-- TOC entry 4140 (class 0 OID 0)
-- Dependencies: 243
-- Name: cooperativas_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.cooperativas_id_seq', 1, false);


--
-- TOC entry 4141 (class 0 OID 0)
-- Dependencies: 229
-- Name: estados_id_estados_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.estados_id_estados_seq', 1, false);


--
-- TOC entry 4142 (class 0 OID 0)
-- Dependencies: 232
-- Name: municipios_id_municipio_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.municipios_id_municipio_seq', 1, false);


--
-- TOC entry 4143 (class 0 OID 0)
-- Dependencies: 210
-- Name: pais_id_pais_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.pais_id_pais_seq', 3, true);


--
-- TOC entry 4144 (class 0 OID 0)
-- Dependencies: 238
-- Name: permisos_id_permiso_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.permisos_id_permiso_seq', 1, false);


--
-- TOC entry 4145 (class 0 OID 0)
-- Dependencies: 254
-- Name: produccion_id_produccion_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.produccion_id_produccion_seq', 1, false);


--
-- TOC entry 4146 (class 0 OID 0)
-- Dependencies: 241
-- Name: registros_id_registro_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.registros_id_registro_seq', 1, false);


--
-- TOC entry 4147 (class 0 OID 0)
-- Dependencies: 235
-- Name: roles_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.roles_id_seq', 1, false);


--
-- TOC entry 4148 (class 0 OID 0)
-- Dependencies: 252
-- Name: tipo_cooperativa_id_clasificacion_cooperativa_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.tipo_cooperativa_id_clasificacion_cooperativa_seq', 1, false);


--
-- TOC entry 4149 (class 0 OID 0)
-- Dependencies: 233
-- Name: users_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.users_id_seq', 1, false);


--
-- TOC entry 3925 (class 2606 OID 75031)
-- Name: apiarios apiarios_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.apiarios
    ADD CONSTRAINT apiarios_pkey PRIMARY KEY (id_apiario);


--
-- TOC entry 3927 (class 2606 OID 75042)
-- Name: colmenas colmenas_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.colmenas
    ADD CONSTRAINT colmenas_pkey PRIMARY KEY (id_colmena);


--
-- TOC entry 3854 (class 2606 OID 50294)
-- Name: continentes continentes_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.continentes
    ADD CONSTRAINT continentes_pkey PRIMARY KEY (id_continente);


--
-- TOC entry 3915 (class 2606 OID 74986)
-- Name: cooperativas cooperativas_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.cooperativas
    ADD CONSTRAINT cooperativas_pkey PRIMARY KEY (id);


--
-- TOC entry 3881 (class 2606 OID 58506)
-- Name: estados estados_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.estados
    ADD CONSTRAINT estados_pkey PRIMARY KEY (id_estado);


--
-- TOC entry 3846 (class 2606 OID 50275)
-- Name: paises geometria_unik_pais; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.paises
    ADD CONSTRAINT geometria_unik_pais UNIQUE (geometria);


--
-- TOC entry 3889 (class 2606 OID 58533)
-- Name: municipios municipios_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.municipios
    ADD CONSTRAINT municipios_pkey PRIMARY KEY (id_municipio);


--
-- TOC entry 3891 (class 2606 OID 58542)
-- Name: municipios nombre_estado_unik_municipio; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.municipios
    ADD CONSTRAINT nombre_estado_unik_municipio UNIQUE (id_estado, nombre);


--
-- TOC entry 3848 (class 2606 OID 66689)
-- Name: paises nombre_unik_pais; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.paises
    ADD CONSTRAINT nombre_unik_pais UNIQUE (nombre);


--
-- TOC entry 3850 (class 2606 OID 50266)
-- Name: paises pais_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.paises
    ADD CONSTRAINT pais_pkey PRIMARY KEY (id_pais);


--
-- TOC entry 3909 (class 2606 OID 74949)
-- Name: permisos permisos_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.permisos
    ADD CONSTRAINT permisos_pkey PRIMARY KEY (id_permiso);


--
-- TOC entry 3933 (class 2606 OID 75086)
-- Name: produccion produccion_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.produccion
    ADD CONSTRAINT produccion_pkey PRIMARY KEY (id_produccion);


--
-- TOC entry 3913 (class 2606 OID 74975)
-- Name: registros registros_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.registros
    ADD CONSTRAINT registros_pkey PRIMARY KEY (id_registro);


--
-- TOC entry 3929 (class 2606 OID 75052)
-- Name: relacion_apiarios_apiculores relacion_apiarios_apiculores_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.relacion_apiarios_apiculores
    ADD CONSTRAINT relacion_apiarios_apiculores_pkey PRIMARY KEY (id_apicultor, id_apiario);


--
-- TOC entry 3917 (class 2606 OID 74991)
-- Name: relacion_cooperativas_administradores relacion_cooperativas_administradores_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.relacion_cooperativas_administradores
    ADD CONSTRAINT relacion_cooperativas_administradores_pkey PRIMARY KEY (id_administrador, id_cooperativa);


--
-- TOC entry 3921 (class 2606 OID 75006)
-- Name: relacion_cooperativas_apicultores relacion_cooperativas_apicultores_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.relacion_cooperativas_apicultores
    ADD CONSTRAINT relacion_cooperativas_apicultores_pkey PRIMARY KEY (id_apicultor, id_cooperativa);


--
-- TOC entry 3911 (class 2606 OID 74954)
-- Name: relacion_permisos_roles relacion_permisos_roles_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.relacion_permisos_roles
    ADD CONSTRAINT relacion_permisos_roles_pkey PRIMARY KEY (id_rol, id_permiso);


--
-- TOC entry 3907 (class 2606 OID 74913)
-- Name: relacion_usuarios_roles relacion_usuarios_roles_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.relacion_usuarios_roles
    ADD CONSTRAINT relacion_usuarios_roles_pkey PRIMARY KEY (id_usuario, id_rol);


--
-- TOC entry 3901 (class 2606 OID 74904)
-- Name: roles roles_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.roles
    ADD CONSTRAINT roles_pkey PRIMARY KEY (id_rol);


--
-- TOC entry 3852 (class 2606 OID 50273)
-- Name: paises siglas_unik_pais; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.paises
    ADD CONSTRAINT siglas_unik_pais UNIQUE (sigla);


--
-- TOC entry 3931 (class 2606 OID 75073)
-- Name: tipo_cooperativa tipo_cooperativa_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.tipo_cooperativa
    ADD CONSTRAINT tipo_cooperativa_pkey PRIMARY KEY (id_clasificacion_cooperativa);


--
-- TOC entry 3856 (class 2606 OID 50296)
-- Name: continentes unik_continentes_nombre; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.continentes
    ADD CONSTRAINT unik_continentes_nombre UNIQUE (nombre);


--
-- TOC entry 3883 (class 2606 OID 58514)
-- Name: estados unik_estados_centroide; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.estados
    ADD CONSTRAINT unik_estados_centroide UNIQUE (centroide);


--
-- TOC entry 3885 (class 2606 OID 58512)
-- Name: estados unik_estados_geometria; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.estados
    ADD CONSTRAINT unik_estados_geometria UNIQUE (geometria);


--
-- TOC entry 3887 (class 2606 OID 58508)
-- Name: estados unik_estados_nombre; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.estados
    ADD CONSTRAINT unik_estados_nombre UNIQUE (nombre);


--
-- TOC entry 3919 (class 2606 OID 75020)
-- Name: relacion_cooperativas_administradores unik_relacion_administrador; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.relacion_cooperativas_administradores
    ADD CONSTRAINT unik_relacion_administrador UNIQUE (id_administrador);


--
-- TOC entry 3923 (class 2606 OID 75018)
-- Name: relacion_cooperativas_apicultores unik_relacion_apicultor; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.relacion_cooperativas_apicultores
    ADD CONSTRAINT unik_relacion_apicultor UNIQUE (id_apicultor);


--
-- TOC entry 3903 (class 2606 OID 74908)
-- Name: roles unik_roles_descricion; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.roles
    ADD CONSTRAINT unik_roles_descricion UNIQUE (descripcion);


--
-- TOC entry 3905 (class 2606 OID 74906)
-- Name: roles unik_roles_nombre; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.roles
    ADD CONSTRAINT unik_roles_nombre UNIQUE (nombre);


--
-- TOC entry 3893 (class 2606 OID 74896)
-- Name: usuarios unik_usuarios_email; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.usuarios
    ADD CONSTRAINT unik_usuarios_email UNIQUE (email);


--
-- TOC entry 3895 (class 2606 OID 74892)
-- Name: usuarios unik_usuarios_identificacion; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.usuarios
    ADD CONSTRAINT unik_usuarios_identificacion UNIQUE (identificacion);


--
-- TOC entry 3897 (class 2606 OID 74894)
-- Name: usuarios unik_usuarios_username; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.usuarios
    ADD CONSTRAINT unik_usuarios_username UNIQUE (username);


--
-- TOC entry 3899 (class 2606 OID 74890)
-- Name: usuarios users_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.usuarios
    ADD CONSTRAINT users_pkey PRIMARY KEY (id_usuario);


--
-- TOC entry 3949 (class 2606 OID 75043)
-- Name: colmenas fk_colmenas_apiarios; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.colmenas
    ADD CONSTRAINT fk_colmenas_apiarios FOREIGN KEY (id_colmena) REFERENCES public.apiarios(id_apiario) ON UPDATE CASCADE ON DELETE CASCADE;


--
-- TOC entry 3944 (class 2606 OID 75074)
-- Name: cooperativas fk_cooperativas_tipo; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.cooperativas
    ADD CONSTRAINT fk_cooperativas_tipo FOREIGN KEY (tipo) REFERENCES public.tipo_cooperativa(id_clasificacion_cooperativa) ON UPDATE CASCADE ON DELETE RESTRICT NOT VALID;


--
-- TOC entry 3935 (class 2606 OID 58515)
-- Name: estados fk_estado_pais_id; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.estados
    ADD CONSTRAINT fk_estado_pais_id FOREIGN KEY (id_pais) REFERENCES public.paises(id_pais) ON UPDATE CASCADE ON DELETE RESTRICT NOT VALID;


--
-- TOC entry 3936 (class 2606 OID 58536)
-- Name: municipios fk_municipio_estado_id; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.municipios
    ADD CONSTRAINT fk_municipio_estado_id FOREIGN KEY (id_estado) REFERENCES public.estados(id_estado) ON UPDATE CASCADE ON DELETE RESTRICT NOT VALID;


--
-- TOC entry 3934 (class 2606 OID 50299)
-- Name: paises fk_pais_continente_nombre; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.paises
    ADD CONSTRAINT fk_pais_continente_nombre FOREIGN KEY (nombre_continente) REFERENCES public.continentes(nombre) ON UPDATE CASCADE ON DELETE SET NULL NOT VALID;


--
-- TOC entry 3952 (class 2606 OID 75087)
-- Name: produccion fk_produccion_colmena; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.produccion
    ADD CONSTRAINT fk_produccion_colmena FOREIGN KEY (id_colmena) REFERENCES public.colmenas(id_colmena) ON UPDATE CASCADE ON DELETE RESTRICT;


--
-- TOC entry 3945 (class 2606 OID 74992)
-- Name: relacion_cooperativas_administradores fk_relacion_administrador; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.relacion_cooperativas_administradores
    ADD CONSTRAINT fk_relacion_administrador FOREIGN KEY (id_administrador) REFERENCES public.usuarios(id_usuario) ON UPDATE CASCADE ON DELETE CASCADE;


--
-- TOC entry 3950 (class 2606 OID 75053)
-- Name: relacion_apiarios_apiculores fk_relacion_apiario; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.relacion_apiarios_apiculores
    ADD CONSTRAINT fk_relacion_apiario FOREIGN KEY (id_apiario) REFERENCES public.apiarios(id_apiario) ON UPDATE CASCADE ON DELETE CASCADE;


--
-- TOC entry 3947 (class 2606 OID 75007)
-- Name: relacion_cooperativas_apicultores fk_relacion_apicultor; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.relacion_cooperativas_apicultores
    ADD CONSTRAINT fk_relacion_apicultor FOREIGN KEY (id_apicultor) REFERENCES public.usuarios(id_usuario) ON UPDATE CASCADE ON DELETE CASCADE;


--
-- TOC entry 3951 (class 2606 OID 75058)
-- Name: relacion_apiarios_apiculores fk_relacion_apicultor; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.relacion_apiarios_apiculores
    ADD CONSTRAINT fk_relacion_apicultor FOREIGN KEY (id_apicultor) REFERENCES public.usuarios(id_usuario) ON UPDATE CASCADE ON DELETE CASCADE;


--
-- TOC entry 3946 (class 2606 OID 74997)
-- Name: relacion_cooperativas_administradores fk_relacion_cooperativa; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.relacion_cooperativas_administradores
    ADD CONSTRAINT fk_relacion_cooperativa FOREIGN KEY (id_cooperativa) REFERENCES public.cooperativas(id) ON UPDATE CASCADE ON DELETE CASCADE NOT VALID;


--
-- TOC entry 3948 (class 2606 OID 75012)
-- Name: relacion_cooperativas_apicultores fk_relacion_cooperativa; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.relacion_cooperativas_apicultores
    ADD CONSTRAINT fk_relacion_cooperativa FOREIGN KEY (id_cooperativa) REFERENCES public.usuarios(id_usuario) ON UPDATE CASCADE ON DELETE CASCADE NOT VALID;


--
-- TOC entry 3942 (class 2606 OID 74960)
-- Name: relacion_permisos_roles fk_relacion_permiso; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.relacion_permisos_roles
    ADD CONSTRAINT fk_relacion_permiso FOREIGN KEY (id_permiso) REFERENCES public.permisos(id_permiso) ON UPDATE CASCADE ON DELETE CASCADE NOT VALID;


--
-- TOC entry 3943 (class 2606 OID 74955)
-- Name: relacion_permisos_roles fk_relacion_rol; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.relacion_permisos_roles
    ADD CONSTRAINT fk_relacion_rol FOREIGN KEY (id_rol) REFERENCES public.roles(id_rol) ON UPDATE CASCADE ON DELETE CASCADE NOT VALID;


--
-- TOC entry 3940 (class 2606 OID 74934)
-- Name: relacion_usuarios_roles fk_relacion_roles; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.relacion_usuarios_roles
    ADD CONSTRAINT fk_relacion_roles FOREIGN KEY (id_rol) REFERENCES public.roles(id_rol) ON UPDATE CASCADE ON DELETE CASCADE NOT VALID;


--
-- TOC entry 3941 (class 2606 OID 74929)
-- Name: relacion_usuarios_roles fk_relacion_usuarios; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.relacion_usuarios_roles
    ADD CONSTRAINT fk_relacion_usuarios FOREIGN KEY (id_usuario) REFERENCES public.usuarios(id_usuario) ON UPDATE CASCADE ON DELETE CASCADE NOT VALID;


--
-- TOC entry 3937 (class 2606 OID 74919)
-- Name: usuarios fk_usuarios_estado_id; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.usuarios
    ADD CONSTRAINT fk_usuarios_estado_id FOREIGN KEY (id_estado) REFERENCES public.estados(id_estado) ON UPDATE CASCADE ON DELETE RESTRICT NOT VALID;


--
-- TOC entry 3938 (class 2606 OID 74914)
-- Name: usuarios fk_usuarios_municipio_id; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.usuarios
    ADD CONSTRAINT fk_usuarios_municipio_id FOREIGN KEY (id_municipio) REFERENCES public.municipios(id_municipio) ON UPDATE CASCADE ON DELETE RESTRICT NOT VALID;


--
-- TOC entry 3939 (class 2606 OID 74924)
-- Name: usuarios fk_usuarios_paises_id; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.usuarios
    ADD CONSTRAINT fk_usuarios_paises_id FOREIGN KEY (id_pais) REFERENCES public.paises(id_pais) ON UPDATE CASCADE ON DELETE RESTRICT NOT VALID;


--
-- TOC entry 4121 (class 0 OID 0)
-- Dependencies: 8
-- Name: SCHEMA public; Type: ACL; Schema: -; Owner: postgres
--

REVOKE USAGE ON SCHEMA public FROM PUBLIC;
GRANT ALL ON SCHEMA public TO PUBLIC;


-- Completed on 2025-01-31 14:16:24

--
-- PostgreSQL database dump complete
--

