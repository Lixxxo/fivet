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

package cl.ucn.disc.pdis.fivet;

import cl.ucn.disc.pdis.fivet.model.Persona;
import cl.ucn.disc.pdis.fivet.orm.DAO;
import cl.ucn.disc.pdis.fivet.orm.ORMLiteDAO;
import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.table.TableUtils;
import lombok.Cleanup;
import lombok.SneakyThrows;

public final class Main {

    @SneakyThrows
    public static void main(String[] args){

        // The Connection
                String databaseUrl = "jdbc:h2:mem:fivet";
        @Cleanup
        JdbcConnectionSource cs = new JdbcConnectionSource((databaseUrl));

        // Create the db
        TableUtils.dropTable(cs, Persona.class, true);
        TableUtils.clearTable(cs, Persona.class);

        DAO<Persona> personaDAO = new ORMLiteDAO<>(cs, Persona.class);

        Persona persona = new Persona(
                "20056208-9",
                "Luis Rojas",
                "luis.rojas04@alumnos.ucn.cl"
                );

        // Save the Persona
        personaDAO.save(persona);

        // Get persona

        personaDAO.get(persona.getId());

        // Get all
        personaDAO.getAll();

        // delete the persona
        personaDAO.delete(persona);

    }
}
