/*
 * Copyright (c)  2023-2023.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package io.drevezadur.scylla.teacher.restserver.lang

import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource
import org.junit.jupiter.params.provider.ValueSource

class LocationTest {

    @ParameterizedTest
    @ValueSource(ints = [-5, -1, 10, 13])
    fun `constructor shall not accept invalid x`(x: Int) {
        assertThatThrownBy {
            Location(x, 3)
        }.isInstanceOf(IllegalArgumentException::class.java)
            .hasMessage("x ('$x') shall be in 0..9 ")
    }

    @ParameterizedTest
    @ValueSource(ints = [-5, -1, 10, 13])
    fun `constructor shall not accept invalid y`(y: Int) {
        assertThatThrownBy {
            Location(3, y)
        }.isInstanceOf(IllegalArgumentException::class.java)
            .hasMessage("y ('$y') shall be in 0..9 ")
    }

    @ParameterizedTest
    @CsvSource("0,0", "9,9", "3,7", "2,8")
    fun `constructor shall accept valid values`(x: Int, y: Int) {
        assertThat(
            Location(x, y)
        ).extracting("x", "y")
            .containsExactly(x, y)
    }


    @Test
    fun `isNeighbour shall return true when location are on same row but with distance`() {
        val loc1 = Location(3, 7)
        val loc2 = Location(3, 5)

        assertThat(loc1.isNeighbour(loc2)).isFalse()
        assertThat(loc2.isNeighbour(loc1)).isFalse()
    }

    @Test
    fun `isNeighbour shall return false when location are on same column but with distance`() {
        val loc1 = Location(2, 5)
        val loc2 = Location(4, 5)

        assertThat(loc1.isNeighbour(loc2)).isFalse()
        assertThat(loc2.isNeighbour(loc1)).isFalse()
    }


    @Test
    fun `isNeighbour shall return true when location are linked on same row`() {
        val loc1 = Location(3, 6)
        val loc2 = Location(3, 5)

        assertThat(loc1.isNeighbour(loc2)).isTrue()
        assertThat(loc2.isNeighbour(loc1)).isTrue()
    }

    @Test
    fun `isNeighbour shall return true when location are linked on same column`() {
        val loc1 = Location(3, 5)
        val loc2 = Location(4, 5)

        assertThat(loc1.isNeighbour(loc2)).isTrue()
        assertThat(loc2.isNeighbour(loc1)).isTrue()
    }

    @Test
    fun `isNeighbour shall return false on same location`() {
        val loc1 = Location(3, 5)
        val loc2 = Location(3, 5)

        assertThat(loc1.isNeighbour(loc2)).isFalse()
        assertThat(loc2.isNeighbour(loc1)).isFalse()
    }


    @Test
    fun getX() {
        assertThat(Location(3, 6).x)
            .isEqualTo(3)
    }

    @Test
    fun getY() {
        assertThat(Location(3, 6).y)
            .isEqualTo(6)
    }
}