/*
 * This file is part of packetevents - https://github.com/retrooper/packetevents
 * Copyright (C) 2024 retrooper and contributors
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package com.github.retrooper.packetevents.protocol.component;

import com.github.retrooper.packetevents.protocol.mapper.MappedEntity;
import com.github.retrooper.packetevents.protocol.nbt.NBT;
import com.github.retrooper.packetevents.protocol.player.ClientVersion;
import com.github.retrooper.packetevents.wrapper.PacketWrapper;
import org.jetbrains.annotations.ApiStatus;

import java.util.function.Function;

public interface ComponentType<T> extends MappedEntity {

    T read(PacketWrapper<?> wrapper);

    void write(PacketWrapper<?> wrapper, T content);

    T decode(NBT nbt, ClientVersion version);

    NBT encode(T value, ClientVersion version);

    @ApiStatus.Internal
    <Z> ComponentType<Z> legacyMap(Function<T, Z> mapper, Function<Z, T> unmapper);

    interface Decoder<T> {

        T decode(NBT nbt, ClientVersion version);
    }

    interface Encoder<T> {

        NBT encode(T value, ClientVersion version);
    }
}
