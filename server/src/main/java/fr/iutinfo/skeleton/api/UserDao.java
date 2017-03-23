                                                                                                                                             package fr.iutinfo.skeleton.api;

import org.skife.jdbi.v2.sqlobject.*;
import org.skife.jdbi.v2.sqlobject.customizers.RegisterMapperFactory;
import org.skife.jdbi.v2.tweak.BeanMapperFactory;

import java.util.List;

public interface UserDao {
    @SqlUpdate("create table users (id integer primary key autoincrement, name varchar(100),"
    		+ " alias varchar(100), email varchar(100), passwdHash varchar(64), salt varchar(64),"
    		+ " search varchar(1024),adresse text"
			+ "validation boolean, telephone text,entreprise text,numSiret text not null,role text)")
    void createUserTable();

    @SqlUpdate("insert into users (name,alias,email, passwdHash, salt, search, "
    		+ "adresse,telephone,entreprise,numSiret)"
    		+ " values (:name, :alias, :email, :passwdHash, :salt, :search,"
    		+ " :adresse, :telephone, :entreprise, :numSiret)")
    @GetGeneratedKeys
    int insert(@BindBean() User user);

    @SqlQuery("select * from users where name = :name")
    @RegisterMapperFactory(BeanMapperFactory.class)
    User findByName(@Bind("name") String name);

    @SqlQuery("select * from users where search like :name")
    @RegisterMapperFactory(BeanMapperFactory.class)
    List<User> search(@Bind("name") String name);

    @SqlUpdate("drop table if exists users")
    void dropUserTable();

    @SqlUpdate("delete from users where id = :id")
    void delete(@Bind("id") int id);

    @SqlQuery("select * from users order by id")
    @RegisterMapperFactory(BeanMapperFactory.class)
    List<User> all();

    @SqlQuery("select * from users where id = :id")
    @RegisterMapperFactory(BeanMapperFactory.class)
    User findById(@Bind("id") int id);
    
    @SqlQuery("update users set name = :name, alias = :alias, email= :email,passwdHash = :passwdHash,"
    		+ "salt = :salt,searh = :search, "
    		+ "adresse = :adresse,telephone = :telephone,entreprise = :entreprise, numSiret = :numSiret where id =:id")
    @RegisterMapperFactory(BeanMapperFactory.class)
    Utilisateurs updateUser(@Bind("id") int id);

    void close();
}
