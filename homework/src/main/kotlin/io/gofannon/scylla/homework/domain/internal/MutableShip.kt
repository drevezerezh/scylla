/*
 * Copyright (c) 2023. gofannon.io
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package io.gofannon.scylla.homework.domain.internal

import io.gofannon.scylla.homework.domain.Ship
import io.gofannon.scylla.homework.lang.Location
import io.gofannon.scylla.homework.lang.ShotResult

internal interface MutableShip : Ship {
    /**
     * Resolve a hit on the ship
     * @param location the location on the ship
     * @return the result of the shot
     */
    fun hitAt(location: Location): ShotResult
}