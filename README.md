# qos-sample
QoS sample application

This is a sample app about QoS on ONOS, you can run it by follow these steps

1. put this in onos/apps dirctory
2. modify onos/modules.defs
   "ONOS_APPS = [
    # Apps
    '//apps/dhcp:onos-apps-dhcp-oar',
    '//apps/dhcprelay:onos-apps-dhcprelay-oar',
    '//apps/fwd:onos-apps-fwd-oar',
    '//apps/qos-sample:onos-apps-qos-sample-oar',
    ...
    "
3. app activate org.onosproject.qos-sample
4. change ovsdb device default driver to ovs by qos-loading-driver command 
   qos-loading-driver ovsdb:127.0.0.1
5. create/delete/query QoS by qos-add/del/query command
   qos-add ovsdb:127.0.0.1 port-name qos/queue-id 1000 1000
   qos-del ovsdb:127.0.0.1 port-name
   qos-query ovsdb:127.0.0.1

