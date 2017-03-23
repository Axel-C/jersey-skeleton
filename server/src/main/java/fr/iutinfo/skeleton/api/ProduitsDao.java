package fr.iutinfo.skeleton.api;

import org.skife.jdbi.v2.sqlobject.*;
import org.skife.jdbi.v2.sqlobject.customizers.RegisterMapperFactory;
import org.skife.jdbi.v2.tweak.BeanMapperFactory;

import java.util.List;

public interface ProduitsDao {
	
	@SqlUpdate("create table produits(idp integer primry key autoincrement,"
			+ "libelle text, reference text,prix float,description text, categorie text,urlImage text)")
	void createProduitsTable();
	
    @SqlUpdate("insert into produits (libelle, reference, prix, description, categorie, urlImage) values"
    		+ " (:libelle, :reference, :prix, :description, :categorie :urlImage)")
    @GetGeneratedKeys
    int insert(@BindBean() Produits produits);

    @SqlQuery("select * from produits where libelle = :libelle")
    @RegisterMapperFactory(BeanMapperFactory.class)
    Produits findByLibelle(@Bind("libelle") String libelle);

    @SqlQuery("select * from Produits where search like :libelle")
    @RegisterMapperFactory(BeanMapperFactory.class)
    List<Produits> search(@Bind("libelle") String libelle);

    @SqlUpdate("drop table if exists produits")
    void dropProduitsTable();

    @SqlUpdate("delete from produits where idp = :idp")
    void delete(@Bind("id") int id);

    @SqlQuery("select * from produits order by idp")
    @RegisterMapperFactory(BeanMapperFactory.class)
    List<Produits> all();

    @SqlQuery("select * from produits where idp = :idp")
    @RegisterMapperFactory(BeanMapperFactory.class)
    Produits findByIdp(@Bind("idp") int idp);

    void close();
}
