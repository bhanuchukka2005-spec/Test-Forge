# Clover coverage experiment

Clover is included as a distinct lab-tool exercise, not required for the default build. It is a commercial Atlassian product and requires an appropriate license. Set `CLOVER_LICENSE` to the license file path, then run `mvn -Pclover clover:setup test clover:clover`. JaCoCo is the default open-source Java coverage tool in this repository.
