import React from "react";
import {Button, StyleSheet, View, NativeModules} from "react-native";

const BlinkModule = NativeModules.RNBlinkId;

export class HomeScreen extends React.Component {

  showReactComponent = () => this.props.navigation.navigate('Scan');

  triggerNativeFragment = () => BlinkModule.startScan();

  render() {
    return (
      <View style={styles.container}>
        <Button
          style={styles.button}
          onPress={this.showReactComponent}
          title={'SHOW FRAGMENT AS NATIVE COMPONENT'}/>
        <Button
          style={styles.button}
          onPress={this.triggerNativeFragment}
          title={'TRIGGER NATIVE FRAGMENT'}/>
      </View>
    );
  }
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    justifyContent: 'center',
    alignItems: 'center',
    backgroundColor: '#F5FCFF',
  },
  button: {
    padding: 10,
    textAlign: 'center',
    color: '#333333',
    margin: 20,
  },
});