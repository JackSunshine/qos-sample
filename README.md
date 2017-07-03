# qos-sample
QoS sample application

This is a sample app about QoS on ONOS, you can run it by follow these steps

1. put this in onos/apps dirctory
2. modify apps/pom.xml 
   add qos-sample to modules
   </modules>
   ....
   	<module>qos-sample</module>
   </modules>
3. modify onos/modules.defs
   ONOS_APPS = [
    # Apps
    '//apps/dhcp:onos-apps-dhcp-oar',
    '//apps/dhcprelay:onos-apps-dhcprelay-oar',
    '//apps/fwd:onos-apps-fwd-oar',
    '//apps/qos-sample:onos-apps-qos-sample-oar',
    ...
4. 
