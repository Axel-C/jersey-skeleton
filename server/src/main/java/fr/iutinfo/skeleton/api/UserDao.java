package fr.iutinfo.skeleton.api;

import org.skife.jdbi.v2.sqlobject.*;
import org.skife.jdbi.v2.sqlobject.customizers.RegisterMapperFactory;
import org.skife.jdbi.v2.tweak.BeanMapperFactory;

import java.util.List;

public interface UserDao {
	@SqlUpdate("create table users (id integer primary key autoincrement, name varchar(100), alias varchar(100), email varchar(100), passwdHash varchar(64), salt varchar(64), search varchar(1024), numSiret varchar(100), telephone varchar(10), role varchar(10))")
	void createUserTable();

	@SqlUpdate("insert into users (name,alias,email, passwdHash, salt, search, numSiret, telephone, role) values (:name, :alias, :email, :passwdHash, :salt, :search, :numSiret, :telephone, :role)")
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

	@SqlUpdate("update users set email = :email, salt = :salt, alias = :alias, telephone = :telephone, passwdHash = :passwdHash where id = :id")
	void update(@BindBean() User user);

	@SqlUpdate("delete from users where id = :id")
	void delete(@Bind("id") int id);

	@SqlQuery("select * from users order by id")
	@RegisterMapperFactory(BeanMapperFactory.class)
	List<User> all();

	@SqlQuery("select * from users where id = :id")
	@RegisterMapperFactory(BeanMapperFactory.class)
	User findById(@Bind("id") int id);

	@SqlQuery("select * from users where email = :email")
	@RegisterMapperFactory(BeanMapperFactory.class)
	User findByEmail(@Bind("email") String email);

	void close();
}