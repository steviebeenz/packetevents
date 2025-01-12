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

package io.github.retrooper.packetevents.sponge.netty;

import com.github.retrooper.packetevents.netty.NettyManager;
import com.github.retrooper.packetevents.netty.buffer.ByteBufAllocationOperator;
import com.github.retrooper.packetevents.netty.buffer.ByteBufOperator;
import com.github.retrooper.packetevents.netty.channel.ChannelOperator;
import io.github.retrooper.packetevents.sponge.netty.buffer.ByteBufAllocationOperatorModernImpl;
import io.github.retrooper.packetevents.sponge.netty.buffer.ByteBufOperatorModernImpl;
import io.github.retrooper.packetevents.sponge.netty.channel.ChannelOperatorModernImpl;

public class NettyManagerImpl implements NettyManager {
    private static final ByteBufOperator BYTE_BUF_OPERATOR = new ByteBufOperatorModernImpl();
    private static final ByteBufAllocationOperator BYTE_BUF_ALLOCATION_OPERATOR = new ByteBufAllocationOperatorModernImpl();
    private static final ChannelOperator CHANNEL_OPERATOR = new ChannelOperatorModernImpl();

    @Override
    public ChannelOperator getChannelOperator() {
        return CHANNEL_OPERATOR;
    }

    @Override
    public ByteBufOperator getByteBufOperator() {
        return BYTE_BUF_OPERATOR;
    }

    @Override
    public ByteBufAllocationOperator getByteBufAllocationOperator() {
        return BYTE_BUF_ALLOCATION_OPERATOR;
    }
}
