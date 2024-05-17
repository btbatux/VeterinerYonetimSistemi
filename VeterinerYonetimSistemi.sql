PGDMP                         |            vetms    15.6    15.6 3    1           0    0    ENCODING    ENCODING        SET client_encoding = 'UTF8';
                      false            2           0    0 
   STDSTRINGS 
   STDSTRINGS     (   SET standard_conforming_strings = 'on';
                      false            3           0    0 
   SEARCHPATH 
   SEARCHPATH     8   SELECT pg_catalog.set_config('search_path', '', false);
                      false            4           1262    19231    vetms    DATABASE     z   CREATE DATABASE vetms WITH TEMPLATE = template0 ENCODING = 'UTF8' LOCALE_PROVIDER = libc LOCALE = 'Turkish_T�rkiye.1254';
    DROP DATABASE vetms;
                postgres    false            �            1259    19669    animal    TABLE       CREATE TABLE public.animal (
    id integer NOT NULL,
    breed character varying(255),
    colour character varying(255),
    date_of_birth date,
    gender character varying(255),
    name character varying(30),
    species character varying(30),
    customer_id integer
);
    DROP TABLE public.animal;
       public         heap    postgres    false            �            1259    19668    animal_id_seq    SEQUENCE     �   CREATE SEQUENCE public.animal_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 $   DROP SEQUENCE public.animal_id_seq;
       public          postgres    false    215            5           0    0    animal_id_seq    SEQUENCE OWNED BY     ?   ALTER SEQUENCE public.animal_id_seq OWNED BY public.animal.id;
          public          postgres    false    214            �            1259    19678    appointment    TABLE     �   CREATE TABLE public.appointment (
    id integer NOT NULL,
    appointment_date timestamp(6) without time zone,
    animal_id integer,
    doctor_id integer
);
    DROP TABLE public.appointment;
       public         heap    postgres    false            �            1259    19677    appointment_id_seq    SEQUENCE     �   CREATE SEQUENCE public.appointment_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 )   DROP SEQUENCE public.appointment_id_seq;
       public          postgres    false    217            6           0    0    appointment_id_seq    SEQUENCE OWNED BY     I   ALTER SEQUENCE public.appointment_id_seq OWNED BY public.appointment.id;
          public          postgres    false    216            �            1259    19685    available_date_range    TABLE     �   CREATE TABLE public.available_date_range (
    id integer NOT NULL,
    end_date date NOT NULL,
    start_date date NOT NULL,
    doctor_id integer
);
 (   DROP TABLE public.available_date_range;
       public         heap    postgres    false            �            1259    19684    available_date_range_id_seq    SEQUENCE     �   CREATE SEQUENCE public.available_date_range_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 2   DROP SEQUENCE public.available_date_range_id_seq;
       public          postgres    false    219            7           0    0    available_date_range_id_seq    SEQUENCE OWNED BY     [   ALTER SEQUENCE public.available_date_range_id_seq OWNED BY public.available_date_range.id;
          public          postgres    false    218            �            1259    19692    customer    TABLE     �   CREATE TABLE public.customer (
    id integer NOT NULL,
    address character varying(100),
    city character varying(100),
    mail character varying(45),
    name character varying(35),
    phone character varying(35)
);
    DROP TABLE public.customer;
       public         heap    postgres    false            �            1259    19691    customer_id_seq    SEQUENCE     �   CREATE SEQUENCE public.customer_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 &   DROP SEQUENCE public.customer_id_seq;
       public          postgres    false    221            8           0    0    customer_id_seq    SEQUENCE OWNED BY     C   ALTER SEQUENCE public.customer_id_seq OWNED BY public.customer.id;
          public          postgres    false    220            �            1259    19699    doctor    TABLE     �   CREATE TABLE public.doctor (
    id integer NOT NULL,
    address character varying(100),
    city character varying(50),
    mail character varying(50),
    name character varying(50),
    phone character varying(20)
);
    DROP TABLE public.doctor;
       public         heap    postgres    false            �            1259    19698    doctor_id_seq    SEQUENCE     �   CREATE SEQUENCE public.doctor_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 $   DROP SEQUENCE public.doctor_id_seq;
       public          postgres    false    223            9           0    0    doctor_id_seq    SEQUENCE OWNED BY     ?   ALTER SEQUENCE public.doctor_id_seq OWNED BY public.doctor.id;
          public          postgres    false    222            �            1259    19706    vaccine    TABLE     �   CREATE TABLE public.vaccine (
    id integer NOT NULL,
    code character varying(25),
    name character varying(50),
    protection_finish_date date,
    protection_start_date date,
    animal_id integer
);
    DROP TABLE public.vaccine;
       public         heap    postgres    false            �            1259    19705    vaccine_id_seq    SEQUENCE     �   CREATE SEQUENCE public.vaccine_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 %   DROP SEQUENCE public.vaccine_id_seq;
       public          postgres    false    225            :           0    0    vaccine_id_seq    SEQUENCE OWNED BY     A   ALTER SEQUENCE public.vaccine_id_seq OWNED BY public.vaccine.id;
          public          postgres    false    224            ~           2604    19672 	   animal id    DEFAULT     f   ALTER TABLE ONLY public.animal ALTER COLUMN id SET DEFAULT nextval('public.animal_id_seq'::regclass);
 8   ALTER TABLE public.animal ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    215    214    215                       2604    19681    appointment id    DEFAULT     p   ALTER TABLE ONLY public.appointment ALTER COLUMN id SET DEFAULT nextval('public.appointment_id_seq'::regclass);
 =   ALTER TABLE public.appointment ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    216    217    217            �           2604    19688    available_date_range id    DEFAULT     �   ALTER TABLE ONLY public.available_date_range ALTER COLUMN id SET DEFAULT nextval('public.available_date_range_id_seq'::regclass);
 F   ALTER TABLE public.available_date_range ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    218    219    219            �           2604    19695    customer id    DEFAULT     j   ALTER TABLE ONLY public.customer ALTER COLUMN id SET DEFAULT nextval('public.customer_id_seq'::regclass);
 :   ALTER TABLE public.customer ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    221    220    221            �           2604    19702 	   doctor id    DEFAULT     f   ALTER TABLE ONLY public.doctor ALTER COLUMN id SET DEFAULT nextval('public.doctor_id_seq'::regclass);
 8   ALTER TABLE public.doctor ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    223    222    223            �           2604    19709 
   vaccine id    DEFAULT     h   ALTER TABLE ONLY public.vaccine ALTER COLUMN id SET DEFAULT nextval('public.vaccine_id_seq'::regclass);
 9   ALTER TABLE public.vaccine ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    225    224    225            $          0    19669    animal 
   TABLE DATA           f   COPY public.animal (id, breed, colour, date_of_birth, gender, name, species, customer_id) FROM stdin;
    public          postgres    false    215   /:       &          0    19678    appointment 
   TABLE DATA           Q   COPY public.appointment (id, appointment_date, animal_id, doctor_id) FROM stdin;
    public          postgres    false    217   �:       (          0    19685    available_date_range 
   TABLE DATA           S   COPY public.available_date_range (id, end_date, start_date, doctor_id) FROM stdin;
    public          postgres    false    219   ;       *          0    19692    customer 
   TABLE DATA           H   COPY public.customer (id, address, city, mail, name, phone) FROM stdin;
    public          postgres    false    221   m;       ,          0    19699    doctor 
   TABLE DATA           F   COPY public.doctor (id, address, city, mail, name, phone) FROM stdin;
    public          postgres    false    223   "<       .          0    19706    vaccine 
   TABLE DATA           k   COPY public.vaccine (id, code, name, protection_finish_date, protection_start_date, animal_id) FROM stdin;
    public          postgres    false    225   �<       ;           0    0    animal_id_seq    SEQUENCE SET     ;   SELECT pg_catalog.setval('public.animal_id_seq', 5, true);
          public          postgres    false    214            <           0    0    appointment_id_seq    SEQUENCE SET     @   SELECT pg_catalog.setval('public.appointment_id_seq', 1, true);
          public          postgres    false    216            =           0    0    available_date_range_id_seq    SEQUENCE SET     I   SELECT pg_catalog.setval('public.available_date_range_id_seq', 9, true);
          public          postgres    false    218            >           0    0    customer_id_seq    SEQUENCE SET     =   SELECT pg_catalog.setval('public.customer_id_seq', 3, true);
          public          postgres    false    220            ?           0    0    doctor_id_seq    SEQUENCE SET     ;   SELECT pg_catalog.setval('public.doctor_id_seq', 4, true);
          public          postgres    false    222            @           0    0    vaccine_id_seq    SEQUENCE SET     <   SELECT pg_catalog.setval('public.vaccine_id_seq', 6, true);
          public          postgres    false    224            �           2606    19676    animal animal_pkey 
   CONSTRAINT     P   ALTER TABLE ONLY public.animal
    ADD CONSTRAINT animal_pkey PRIMARY KEY (id);
 <   ALTER TABLE ONLY public.animal DROP CONSTRAINT animal_pkey;
       public            postgres    false    215            �           2606    19683    appointment appointment_pkey 
   CONSTRAINT     Z   ALTER TABLE ONLY public.appointment
    ADD CONSTRAINT appointment_pkey PRIMARY KEY (id);
 F   ALTER TABLE ONLY public.appointment DROP CONSTRAINT appointment_pkey;
       public            postgres    false    217            �           2606    19690 .   available_date_range available_date_range_pkey 
   CONSTRAINT     l   ALTER TABLE ONLY public.available_date_range
    ADD CONSTRAINT available_date_range_pkey PRIMARY KEY (id);
 X   ALTER TABLE ONLY public.available_date_range DROP CONSTRAINT available_date_range_pkey;
       public            postgres    false    219            �           2606    19697    customer customer_pkey 
   CONSTRAINT     T   ALTER TABLE ONLY public.customer
    ADD CONSTRAINT customer_pkey PRIMARY KEY (id);
 @   ALTER TABLE ONLY public.customer DROP CONSTRAINT customer_pkey;
       public            postgres    false    221            �           2606    19704    doctor doctor_pkey 
   CONSTRAINT     P   ALTER TABLE ONLY public.doctor
    ADD CONSTRAINT doctor_pkey PRIMARY KEY (id);
 <   ALTER TABLE ONLY public.doctor DROP CONSTRAINT doctor_pkey;
       public            postgres    false    223            �           2606    19711    vaccine vaccine_pkey 
   CONSTRAINT     R   ALTER TABLE ONLY public.vaccine
    ADD CONSTRAINT vaccine_pkey PRIMARY KEY (id);
 >   ALTER TABLE ONLY public.vaccine DROP CONSTRAINT vaccine_pkey;
       public            postgres    false    225            �           2606    19717 '   appointment fk2kkeptdxfuextg5ch7xp3ytie    FK CONSTRAINT     �   ALTER TABLE ONLY public.appointment
    ADD CONSTRAINT fk2kkeptdxfuextg5ch7xp3ytie FOREIGN KEY (animal_id) REFERENCES public.animal(id);
 Q   ALTER TABLE ONLY public.appointment DROP CONSTRAINT fk2kkeptdxfuextg5ch7xp3ytie;
       public          postgres    false    217    3205    215            �           2606    19712 "   animal fk6pvxm5gfjqxclb651be9unswe    FK CONSTRAINT     �   ALTER TABLE ONLY public.animal
    ADD CONSTRAINT fk6pvxm5gfjqxclb651be9unswe FOREIGN KEY (customer_id) REFERENCES public.customer(id);
 L   ALTER TABLE ONLY public.animal DROP CONSTRAINT fk6pvxm5gfjqxclb651be9unswe;
       public          postgres    false    221    3211    215            �           2606    19727 0   available_date_range fk7ncoqu85mml8wsb6ideah391n    FK CONSTRAINT     �   ALTER TABLE ONLY public.available_date_range
    ADD CONSTRAINT fk7ncoqu85mml8wsb6ideah391n FOREIGN KEY (doctor_id) REFERENCES public.doctor(id);
 Z   ALTER TABLE ONLY public.available_date_range DROP CONSTRAINT fk7ncoqu85mml8wsb6ideah391n;
       public          postgres    false    3213    219    223            �           2606    19732 #   vaccine fkne3kmh8y5pcyxwl4u2w9prw6j    FK CONSTRAINT     �   ALTER TABLE ONLY public.vaccine
    ADD CONSTRAINT fkne3kmh8y5pcyxwl4u2w9prw6j FOREIGN KEY (animal_id) REFERENCES public.animal(id);
 M   ALTER TABLE ONLY public.vaccine DROP CONSTRAINT fkne3kmh8y5pcyxwl4u2w9prw6j;
       public          postgres    false    225    215    3205            �           2606    19722 '   appointment fkoeb98n82eph1dx43v3y2bcmsl    FK CONSTRAINT     �   ALTER TABLE ONLY public.appointment
    ADD CONSTRAINT fkoeb98n82eph1dx43v3y2bcmsl FOREIGN KEY (doctor_id) REFERENCES public.doctor(id);
 Q   ALTER TABLE ONLY public.appointment DROP CONSTRAINT fkoeb98n82eph1dx43v3y2bcmsl;
       public          postgres    false    223    3213    217            $   �   x�-�K
�0 ���.�|Zp[i�ō`7��H�V�i<�w�{�av�������a�!���q�9Je4�4XzI�����������f]�N!�-8�(�٩�:�1����1r=���z^� 1���}�;��v�����geRd�������?�!�J>4�      &   $   x�3�4202�50�54U04�2 !NCNC�=... V      (   G   x�=���0C�x��4������i%��ajڽu�
��݈�5Tf��Is�7��4�Ǭ��� ��%      *   �   x�U�M
�0��/��	��s #U)bjB7��i�Rci{+���j\�f10������꺦f�RL�7�-��[��9�顗�z��O\Hʘ�I��e�r��� W�{6oH�y��<}a��xOy��c��)�9�A�{I���
;��#q��2B�'>      ,   �   x�U�K�0����sR���|&&.q�f,U&�HJ1r,���6�dV_2&����� E빹^��Ju�C�4r�5��9���QA�,M�|��o�h<n8�=Y��/F��Y� e,��J� ��~=�wP[nXú�AE����eu��N���x�� ���55y�ͬ&�s���_g="��M��y�gY���H�׃X      .   P   x�3��022�KL�4202�50�5�1��LCNC.3ΐ0CCCΐĲ�����?����F�C# 1M�L�b���� �6�     