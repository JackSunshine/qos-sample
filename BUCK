COMPILE_DEPS = [
    '//lib:CORE_DEPS',
    '//core/store/serializers:onos-core-serializers',
    '//core/store/primitives:onos-core-primitives',
    '//core/api:onos-api',
    '//lib:org.apache.karaf.shell.console',
    '//cli:onos-cli',
]

osgi_jar_with_tests (
    deps = COMPILE_DEPS,
)

onos_app (
    title = 'QoS Sample App',
    category = 'Traffic Limiting',
    url = 'http://onosproject.org',
    description = 'QoS sample application using ovsdb protocol.',
    required_apps = [ 'org.onosproject.ovsdb-base','org.onosproject.drivers.ovsdb' ],
)
