/*
 * This file is part of packetevents - https://github.com/retrooper/packetevents
 * Copyright (C) 2021 retrooper and contributors
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

package io.github.retrooper.packetevents.handlers.modern.early;

import io.github.retrooper.packetevents.PacketEvents;
import io.github.retrooper.packetevents.manager.server.ServerVersion;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

public class ServerChannelHandlerModern extends ChannelInboundHandlerAdapter {
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        Channel channel = (Channel) msg;
        //TODO Test injector on 1.11, 1.12 and 1.8 to check if this check if correct
        if (PacketEvents.get().getServerManager().getVersion().isNewerThanOrEquals(ServerVersion.v_1_12)) {
            channel.pipeline().addLast(PacketEvents.get().connectionInitializerName, new PreChannelInitializerModern_v1_12());
        }
        else {
            channel.pipeline().addFirst(PacketEvents.get().connectionInitializerName, new PreChannelInitializerModern_v1_8());
        }
        super.channelRead(ctx, msg);
    }
}
