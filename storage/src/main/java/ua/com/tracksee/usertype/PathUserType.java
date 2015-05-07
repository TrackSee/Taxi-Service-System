package ua.com.tracksee.usertype;

import org.hibernate.HibernateException;
import org.hibernate.usertype.UserType;
import org.postgresql.geometric.PGpath;

import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

/**
 * Created by Maria Komar on 04.05.2015.
 */
public class PathUserType implements UserType, Serializable{
    @Override
    public Object nullSafeGet(ResultSet resultSet, String[] strings, Object o) throws HibernateException, SQLException {
        assert strings.length == 1;
        if (resultSet.wasNull())
        {
            return null;
        }
        final PGpath path = null;
        String tmp = new String(resultSet.getObject(strings[0]).toString());
        path.setValue(tmp);
        return path;
    }

    @Override
    public void nullSafeSet(PreparedStatement preparedStatement, Object o, int i) throws HibernateException, SQLException {
        preparedStatement.setObject(i, o);
    }

    @Override
    public int[] sqlTypes()
    {
        return new int[]
                {
                        Types.OTHER
                };
    }

    @SuppressWarnings("rawtypes")
    @Override
    public Class returnedClass()
    {
        return PGpath.class;
    }

    @Override
    public boolean equals(Object o, Object o1) throws HibernateException
    {
        boolean isEqual = false;
        if (o == o1)
        {
            isEqual = true;
        }
        if (o == null || o1 == null)
        {
            isEqual = false;
        }
        else
        {
            isEqual = o.equals(o1);
        }
        return isEqual;
    }

    @Override
    public int hashCode(Object o) throws HibernateException
    {
        return o.hashCode();
    }

    @Override
    public Object deepCopy(Object o) throws HibernateException
    {
        return (Serializable) o;
    }

    @Override
    public boolean isMutable()
    {
        return true;
    }

    @Override
    public Serializable disassemble(Object o) throws HibernateException
    {
        return (Serializable) o;
    }

    @Override
    public Object assemble(final Serializable srlzbl, final Object o) throws HibernateException
    {
        return srlzbl;
    }

    @Override
    public Object replace(Object o, Object o1, Object o2) throws HibernateException
    {
        return this;
    }

}
