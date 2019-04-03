import React from "react";
import {requireNativeComponent} from "react-native";

const ScanCardView = requireNativeComponent('ScanCardView');

export class ScanScreen extends React.Component {

  render() {
    return (
      <ScanCardView style={{flex: 1}}/>
    );
  }
}