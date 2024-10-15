
package com.ra.demo1.model.dao.impl;

import com.ra.demo1.model.dao.IUserDao;
import com.ra.demo1.model.entity.Users;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserDaoImpl implements IUserDao
{
    @Autowired
    private SessionFactory sessionFactory;

    // str
    @Override
    public List<Users> findAll(int page, int size)
    {
        try (Session session = sessionFactory.openSession())
        {
            return session.createQuery("from Users",Users.class)
                    // tương đương với offset bắt đầu từ phần tử thứ bao nhiêu ví dụ 5 thì bắt đầu từ vị trí thứ 5 luôn
                    .setFirstResult(page * size)
                    // tương đương với limit giới hạn phần tử
                    .setMaxResults(size)
                    .list();
        }
        catch (Exception e)
        {
            throw new RuntimeException(e);
        }
    }

    // Lấy totalElement ra để tính totalPages bằng công thức -> totalElements / size và phải làm tròn lên
    @Override
    public long totalElement()
    {
        try (Session session = sessionFactory.openSession())
        {
            return session.createQuery("select count(u) from Users u",Long.class)
                    .getSingleResult();
        }
        catch (Exception e)
        {
            throw new RuntimeException(e);
        }
    }
}
