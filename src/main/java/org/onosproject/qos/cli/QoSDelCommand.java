/*
 * Copyright 2017-present Open Networking Laboratory
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
package org.onosproject.qos.cli;

import org.apache.karaf.shell.commands.Command;
import org.onlab.osgi.DefaultServiceDirectory;
import org.onlab.util.Bandwidth;
import org.onosproject.cli.AbstractShellCommand;
import org.apache.karaf.shell.commands.Argument;
import org.onosproject.net.Device;
import org.onosproject.net.DeviceId;
import org.onosproject.net.Port;
import org.onosproject.net.behaviour.DefaultQosDescription;
import org.onosproject.net.behaviour.DefaultQueueDescription;
import org.onosproject.net.behaviour.PortConfigBehaviour;
import org.onosproject.net.behaviour.QosConfigBehaviour;
import org.onosproject.net.behaviour.QosDescription;
import org.onosproject.net.behaviour.QosId;
import org.onosproject.net.behaviour.QueueConfigBehaviour;
import org.onosproject.net.behaviour.QueueDescription;
import org.onosproject.net.behaviour.QueueId;
import org.onosproject.net.device.DefaultPortDescription;
import org.onosproject.net.device.DeviceService;
import org.onosproject.net.device.PortDescription;

/**
 * Delete Qos Command.
 */
@Command(scope = "onos", name = "qos-del",
        description = "Qos Test.")
public class QoSDelCommand extends AbstractShellCommand {

    @Argument(index = 0, name = "ovsdbDeviceId", description = "the ovsdb TYPE id.", required = true,
            multiValued = false)
    String ovsdbDeviceId = null;

    @Argument(index = 1, name = "queue and qos id", description = "queue and qos id.", required = true,
            multiValued = false)
    String id = null;

    @Argument(index = 2, name = "port name", description = "the port name.", required = true,
            multiValued = false)
    String portName = null;

    @Override
    protected void execute() {
        DeviceService deviceService = DefaultServiceDirectory.getService(DeviceService.class);
        Device device = deviceService.getDevice(DeviceId.deviceId(ovsdbDeviceId));
        if (device == null) {
            log.error("{} isn't support config.", ovsdbDeviceId);
            return;
        }

        QueueDescription queueDesc = DefaultQueueDescription.builder()
                .queueId(QueueId.queueId(id))
                .build();

        Port port = deviceService.getPorts(DeviceId.deviceId(ovsdbDeviceId))
                .stream().filter(p -> p.number().name().equals(portName))
                .findFirst().orElse(null);
        PortDescription portDesc = new DefaultPortDescription(port.number(), true);

        QosDescription qosDesc = DefaultQosDescription.builder()
                .qosId(QosId.qosId(id))
                .type(QosDescription.Type.HTB)
                .build();

        QueueConfigBehaviour queueConfig = device.as(QueueConfigBehaviour.class);
        QosConfigBehaviour qosConfig = device.as(QosConfigBehaviour.class);
        PortConfigBehaviour portConfig = device.as(PortConfigBehaviour.class);

        queueConfig.deleteQueue(queueDesc.queueId());
        qosConfig.deleteQoS(qosDesc.qosId());
        portConfig.removeQoS(portDesc.portNumber());
    }
}
