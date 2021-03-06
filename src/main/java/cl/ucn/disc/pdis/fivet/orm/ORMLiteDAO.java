/*
 * MIT License
 *
 * Copyright (c) 2022.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package cl.ucn.disc.pdis.fivet.orm;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.support.ConnectionSource;
import lombok.NonNull;
import lombok.SneakyThrows;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

/**
 * The ORMLite implementation of DAO.
 *
 * @author lrojas
 */
public final class ORMLiteDAO <T extends BaseEntity> implements DAO<T>{

    /**
     * The real DAO (connection to ORMLite DAO)
     */
    private final Dao<T, Integer> theDAO;

    /**
     * The Constructor of ORMLiteDAO.
     * @param cs the connection to the database.
     * @param clazz the type of T.
     */
    @SneakyThrows(SQLException.class)
    public ORMLiteDAO(
                @NonNull final ConnectionSource cs,
                @NonNull final Class<T> clazz) {

        this.theDAO = DaoManager.createDao(cs, clazz);
    }


    /**
     * Get optional T.
     *
     * @param id the id of the t.
     */
    @SneakyThrows(SQLException.class)
    @Override
    public Optional<T> get(final Integer id) {
        // Exec the SQL
        T t = theDAO.queryForId(id);
        return t == null ? Optional.empty() : Optional.of(t);

    }

    /**
     * Get all the Ts.
     */
    @SneakyThrows(SQLException.class)
    @Override
    public List<T> getAll() {
        return this.theDAO.queryForAll();
    }

    /**
     * Save a T.
     *
     * @param t to save
     */
    @SneakyThrows(SQLException.class)
    @Override
    public void save(T t) {

        int created = this.theDAO.create(t);

        if (created != 1){
            throw new SQLException("Rows created =! 1");
        }

    }

    /**
     * Delete a T.
     *
     * @param id of t to delete.
     */
    @SneakyThrows(SQLException.class)
    @Override
    public void delete(Integer id) {

        int deleted = this.theDAO.deleteById(id);

        if (deleted != 1){
            throw new SQLException("Rows deleted != 1");
        }
    }

    /**
     * Delete a T.
     *
     * @param t to delete;
     */
    //@SneakyThrows(SQLException.class)
    @Override
    public void delete(T t) {
        this.delete(t.getId());
    }
}
